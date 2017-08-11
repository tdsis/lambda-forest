package br.com.tdsis.lambda.forest.http.handler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdsis.lambda.forest.domain.DefaultResponseError;
import br.com.tdsis.lambda.forest.http.DeserializerStrategy;
import br.com.tdsis.lambda.forest.http.HttpMethod;
import br.com.tdsis.lambda.forest.http.RequestBodyDeserializerStrategy;
import br.com.tdsis.lambda.forest.http.ResponseBodySerializerStrategy;
import br.com.tdsis.lambda.forest.http.SerializerStrategy;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.request.HttpRequest;
import br.com.tdsis.lambda.forest.http.response.ResponseEntity;
import br.com.tdsis.lambda.forest.http.validation.DefaultRequestValidator;
import br.com.tdsis.lambda.forest.http.validation.RequestValidator;

/**
 * AbstractRequestHandler is the abstract base class that handles
 * a lambda function call.  
 * <p>
 * The handling process consists in the following steps:
 * <ul>
 * <li>Init the request attributes (headers, path parameters, etc)</li>
 * <li>Deserialize the request body</li>
 * <li>Validate the request</li>
 * <li>Serialize the response</li> 
 * </ul>
 * 
 * @param <I> The input class
 * @param <O> The output class
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link RequestHandler}
 */
public abstract class AbstractRequestHandler<I, O> implements RequestHandler<HttpRequest, ResponseEntity> {

    /**
     * The default request body deserializer strategy.
     * <p>
     * A JSON deserializer will be assumed if the request header
     * "contentType" is not registered in {@code DeserializerStrategy}
     */
    public static final DeserializerStrategy DEFAULT_DESERIALIZER_STRATEGY = DeserializerStrategy.APPLICATION_JSON; 
    
    /**
     * The default response body serializer strategy.
     * <p>
     * A JSON serializer will be assumed if the request header
     * "accept" is not registered in {@code SerializerStrategy}
     */
    public static final SerializerStrategy DEFAULT_SERIALIZER_STRATEGY = SerializerStrategy.APPLICATION_JSON;
    
    /**
     * The default request validator.
     * <p>
     * A request validation will be performed if the first parameter of the method 
     * {@link AbstractRequestHandler#execute(Object, Context)} is annotated with {@code Valid}
     */
    public static final RequestValidator DEFAULT_REQUEST_VALIDATOR = new DefaultRequestValidator();
    
    /**
     * The default content type.
     * <p>
     * application/json is the default content type for this lambda funcion handler
     */
    public static final String DEFAULT_CONTENT_TYPE = ContentType.APPLICATION_JSON.getMimeType();
        
    /**
     * The default error message.
     * <p>
     * This is the default error message to return if the handler 
     * is unable to process the request.
     */
    public static final String DEFAULT_ERROR_MESSAGE = "Unable to process the request";
    
    /*
     * The object mapper
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /*
     * Attribute used in the shouldValidate method.
     */
    private static final String EXECUTE_METHOD = "execute";
    
    /*
     * The parameterized input of the class
     */
    private Class<?> parameterizedInput;
    
    /*
     * The parameterized output of the class
     */
    private Class<?> parameterizedOutput;
    
    /*
     * The request headers
     */
    private Map<String, String> headers;
        
    /*
     * The stage variables
     */
    private Map<String, String> stageVariables;
    
    /*
     * The raw request body
     */
    private String rawRequestBody;
    
    /*
     * The http method of the request
     */
    private String httpMethod;
    
    /*
     * The path of the request
     */
    private String path;
    
    /*
     * the path parameters of the request
     */
    private Map<String, String> pathParameters;
    
    /*
     * The query string parameters of the request
     */
    private Map<String, String> queryStringParameters;
    
    /*
     * The resource
     */
    private String resource;
    
    /*
     * The additional properties
     */
    private Map<String, String> additionalProperties;
    
    
    /*
     * The response headers
     */
    private Map<String, String> responseHeaders = new HashMap<>();
    
    /**
     * The default constructor.
     */
    public AbstractRequestHandler() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();     
        this.parameterizedInput = (Class<?>) type.getActualTypeArguments()[0];
        this.parameterizedOutput = (Class<?>) type.getActualTypeArguments()[1];
    }
    
    @SuppressWarnings("unchecked")
    public ResponseEntity handleRequest(HttpRequest request, Context context) {                 
        ResponseBodySerializerStrategy serializer = null;
        RequestBodyDeserializerStrategy deserializer = null;
        RequestValidator validator = null;
        
        I input = null;
        Object output = null;
        
        int httpStatusCode = HttpStatus.SC_OK;      
        
        try {       
            
            initRequestAttributes(request);
                        
            String contentType = getHeader(HttpHeaders.CONTENT_TYPE).orElse(DEFAULT_CONTENT_TYPE);          
            String accept = getHeader(HttpHeaders.ACCEPT).orElse(DEFAULT_CONTENT_TYPE);
            
            deserializer = resolveDeserializerStrategy(contentType);                        
            serializer = resolveSerializerStrategy(accept);
            
            before(context);
            
            boolean isDeserializable = isDeserializable();
            if (isDeserializable)
                input = deserializer.deserialize(
                            rawBodyOrQueryString(), 
                            parameterizedInput);
            else
                input = isVoidInput() ? null : (I) request;
            
            if (shouldValidade() && isDeserializable) {
                validator = resolveRequestValidator();
                validator.validate(input);
            }
            
            output = execute(input, context);           
            if (!isSerializable() && !isVoidOutput())
                return (ResponseEntity) output;
            
        } catch (HttpException e) {
            output = e.getEntity();
            httpStatusCode = e.getHttpStatusCode();
            
        } catch (Exception e) {         
            output = new DefaultResponseError(DEFAULT_ERROR_MESSAGE);
            httpStatusCode = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
        
        return ResponseEntity.of(output, 
                getResponseHeaders(), 
                httpStatusCode,                 
                serializer);
    }
            
    /**
     * Returns the raw request body
     * 
     * @return rawRequestBody The raw request body
     */
    protected String getRawRequestBody() {
        return rawRequestBody;
    }
    
    /**
     * Returns the http method
     * 
     * @return httpMethod The http method of the request
     */
    protected String getHttpMethod() {
        return httpMethod;
    }
    
    /**
     * Returns the resource
     * 
     * @return resource The resource
     */
    protected String getResource() {
        return resource;
    }
    
    /**
     * Returns the path of the request
     * 
     * @return path The path of the request
     */
    protected String getPath() {
        return path;
    }
    
    /**
     * Returns all the request headers
     *  
     * @return headers All the request headers
     */
    protected Map<String, String> getHeaders() {
        return headers;
    }
    
    /**
     * Returns the value of the given header name
     * 
     * @param key The header name
     * @return value The header value
     */
    protected Optional<String> getHeader(String key) {
        return Optional.ofNullable(headers.get(key));
    }
    
    /**
     * Returns all the stage variables
     * 
     * @return stageVariables All the stage variables
     */
    protected Map<String, String> getStageVariables() {
        return stageVariables;
    }
    
    /**
     * Returns the value of the given stage variable name
     * 
     * @param key The stage variable name
     * @return value The stage variable value
     */
    protected Optional<String> getStageVariable(String key) {
        return Optional.ofNullable(stageVariables.get(key));
    }
    
    /**
     * Returns all the path parameters
     * 
     * @return pathParameters All the path parameters
     */
    protected Map<String, String> getPathParameters() {
        return pathParameters;
    }
    
    /**
     * Returns the value of the given path parameter name
     * 
     * @param key The path parameter name
     * @return value The path parameter value
     */
    protected Optional<String> getPathParameter(String key) {
        return Optional.ofNullable(pathParameters.get(key));
    }
    
    
    /**
     * Returns all the query string parameters
     * 
     * @return queryStringParameters All the query string parameters
     */
    protected Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }
    
    /**
     * Returns the value of the given query string parameter name
     * 
     * @param key The query string parameter name
     * @return value The value of the query string parameter 
     */
    protected Optional<String> getQueryStringParameter(String key) {
        return Optional.ofNullable(queryStringParameters.get(key));
    }
    
    /**
     * Returns all the additional properties
     * 
     * @return additionalProperties All the additional properties
     */
    protected Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }
    
    /**
     * Returns the value of the given additional property name
     * 
     * @param key The additional property name
     * @return value The value of the additional property
     */
    protected Optional<String> getAdditionalProperty(String key) {
        return Optional.ofNullable(additionalProperties.get(key));
    }
        
    /**
     * Returns the response headers
     * 
     * @return responseHeaders The response headers
     */
    protected Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }
    
    /**
     * Adds a response header
     * 
     * @param key The header name
     * @param value The header value
     */
    protected void addResponseHeader(String key, String value) {
        responseHeaders.put(key, value);
    }
    
    
    /**
     * Returns the request body deserializer strategy
     * <p>
     * If no deserializer is found for the given content type
     * a default request body deserializer will be assumed.
     * 
     * @param contentType The content type
     * @return strategy The
     * @see {@link AbstractRequestHandler#DEFAULT_DESERIALIZER_STRATEGY}
     */
    protected RequestBodyDeserializerStrategy resolveDeserializerStrategy(String contentType) {
        return DeserializerStrategy.strategy(contentType)
                .orElse(DEFAULT_DESERIALIZER_STRATEGY)
                .getRequestBodyDeserializer();
    }
    
    /**
     * Returns the response body serializer strategy
     * <p>
     * If no serializer is found for the given "accept" header
     * a default response body serializer will be assumed.
     *  
     * @param accept The accept header value
     * @return strategy The response body serializer strategy
     * @see {@link AbstractRequestHandler#DEFAULT_SERIALIZER_STRATEGY}
     */
    protected ResponseBodySerializerStrategy resolveSerializerStrategy(String accept) {
        return SerializerStrategy.strategy(accept)
                .orElse(DEFAULT_SERIALIZER_STRATEGY)
                .getResponseBodySerializer();
    }
    
    /**
     * Returns the request body validator
     * 
     * @return validator The request body validator
     * @see {@link AbstractRequestHandler#DEFAULT_REQUEST_VALIDATOR}
     */
    protected RequestValidator resolveRequestValidator() {
        return DEFAULT_REQUEST_VALIDATOR;
    }
    
    /**
     * Creates a new instance if the given Map is null
     * 
     * @param map The map object to be initialized
     * @return map A new instance of a Map if the given object is null.
     *             The same instance of the object will be returned otherwise.
     */
    private Map<String, String> initOrDefaultHashMap(Map<String, String> map) {
        return Objects.isNull(map) ? new HashMap<>() : map;
    }
    
    /**
     * Returns the raw body to be deserialized.
     * <p>
     * If the request method is GET the method will return
     * a JSON represetation of the query string parameters.
     * Any other method will return the raw body request.
     * 
     * @return rawBody The raw body to be deserialized
     * @throws JsonProcessingException 
     */
    private String rawBodyOrQueryString() throws JsonProcessingException {
        if (!HttpMethod.GET.name().equals(httpMethod))
            return rawRequestBody;
        
        return MAPPER.writeValueAsString(queryStringParameters);
    }
    
    /**
     * Checks if the parameterized input should be deserialized.
     * <p>
     * If the parameterized input has the type of {@link HttpRequest} 
     * or {@link Void} class the deserialization process should not be performed.
     *  
     * @return isDeserializable false if the parameterized input class is the {@link HttpRequest} class.
     *                          true otherwise  
     */
    private boolean isDeserializable() {
        return !HttpRequest.class.isAssignableFrom(parameterizedInput)
                && !Void.class.isAssignableFrom(parameterizedInput);
    }
    
    /**
     * Checks if the parameterized output should be serialized.
     * <p>
     * If the parameterized output has the type of {@link ResponseEntity} 
     * or {@link Void} class the serialization process should not be performed.
     *  
     * @return isSerializable false if the parameterized output class is the {@link ResponseEntity} class.
     *                        true otherwise    
     */
    private boolean isSerializable() {
        return !ResponseEntity.class.isAssignableFrom(parameterizedOutput)
                && !Void.class.isAssignableFrom(parameterizedOutput);
    }
    
    /**
     * Checks if the parameterized input class
     * is assignable from Void class
     * 
     * @return assignable true if the parameterized input class 
     * 				 	  is assignable from Void class. false otherwise
     */
    private boolean isVoidInput() {
        return Void.class.isAssignableFrom(parameterizedInput);
    }
    
    /**
     * Checks if the parameterized ouput class
     * is assignable from Void class
     * 
     * @return assignable true if the parameterized input class 
     * 				 	  is assignable from Void class. false otherwise
     */
    private boolean isVoidOutput() {
        return Void.class.isAssignableFrom(parameterizedOutput);
    }
    
    /**
     * Checks if the request body should be validated
     * <p>
     * A request body validation is performed if the input argument of 
     * the method {@link AbstractRequestHandler#execute(Object, Context)} contains the
     * {@link Valid} annotation.
     * 
     * @return should true if the input argument has the {@link Valid} annotation 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     */
    private boolean shouldValidade() throws NoSuchMethodException, SecurityException {
        Method method = getClass().getMethod(EXECUTE_METHOD, 
                parameterizedInput, 
                Context.class);
        
        Annotation[][] annotations = method.getParameterAnnotations();
        Annotation[] firstArgAnnotations = Arrays.stream(annotations)
                .findFirst()
                .get();
        
        return Arrays.stream(firstArgAnnotations)                                               
                .anyMatch(a -> a instanceof Valid);         
    }
    
    /**
     * Inits the request attributes
     * 
     * @param request
     */
    private void initRequestAttributes(HttpRequest request) {
        rawRequestBody = request.getBody();
        httpMethod = request.getHttpMethod();
        path = request.getPath();
        resource = request.getResource();
        
        headers = initOrDefaultHashMap(request.getHeaders());
        stageVariables = initOrDefaultHashMap(request.getStageVariables());
        pathParameters = initOrDefaultHashMap(request.getPathParameters());
        queryStringParameters = initOrDefaultHashMap(request.getQueryStringParameters());
        additionalProperties = initOrDefaultHashMap(request.getAdditionalProperties());
    }
    
    /**
     * The before method will be called before the 
     * request body deserialization and validation process.
     * <p>
     * It can be used setup any configuration before the execution.
     * <p>
     * <b>Eg.:</b>
     * <pre>
     * <code>
     *  {@literal @}Override
     *  public void before(Context context) throws HttpException {
     *      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
     *  }
     * </code>
     * </pre> 
     * @param context The Lambda execution environment context
     * @throws HttpException
     */
    public abstract void before(Context context) throws HttpException;
    
    /**
     * The lambda execution handler method.
     * <p>
     * The execute method will be called after the 
     * request body deserialization and validation process.
     * 
     * @param input   The request body input object
     * @param context The Lambda execution environment context
     * @return output The response body output 
     */
    public abstract O execute(I input, Context context) throws HttpException;
    
}
