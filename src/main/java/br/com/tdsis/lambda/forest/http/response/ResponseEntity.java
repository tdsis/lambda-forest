package br.com.tdsis.lambda.forest.http.response;

import java.util.Map;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdsis.lambda.forest.http.ResponseBodySerializerStrategy;
import br.com.tdsis.lambda.forest.http.exception.HttpException;

/**
 * The ResponseEntity class
 * <p>
 * It represents the http response structure that 
 * the AWS API Gateway expects when using integration proxy.
 *
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see <a href="http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-set-up-simple-proxy.html">
 *          http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-set-up-simple-proxy.html</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseEntity {
        
    private String body;    
    private Map<String, String> headers;    
    private int statusCode;     
    
    /**
     * Creates a new ResponseEntity with 
     * the given response body object and 
     * a http status code 200.
     * 
     * @param body The response body object
     * @return responseEntity The response entity object
     */
    public static ResponseEntity of(Object body) {
        return of(body, null);
    }
    
    /**
     * Creates a new ResponseEntity with 
     * the given response body object, the given  
     * response headers and a http status code 200.
     *  
     * @param body    The response body object
     * @param headers The response headers
     * @return responseEntity The response entity object
     */
    public static ResponseEntity of(Object body, Map<String, String> headers) {
        return of(body, headers, HttpStatus.SC_OK);
    }
    
    /**
     * Creates a new ResponseEntity with 
     * the given response body object and 
     * the given http status code.
     *   
     * @param body            The response body object
     * @param httpStatusCode  The http status code
     * @return responseEntity The response entity object
     */
    public static ResponseEntity of(Object body, int httpStatusCode) {
        return of(body, null, httpStatusCode);
    }
    
    /**
     * Creates a new ResponseEntity with 
     * the given response body object, the given  
     * response headers and the given http status code.
     *  
     * @param body            The response body object
     * @param headers         The response headers
     * @param httpStatusCode  The http status code
     * @return responseEntity The response entity object
     */
    public static ResponseEntity of(Object body, Map<String, String> headers, int httpStatusCode) {
        return of(body, headers, httpStatusCode, null);
    }
    
    /**
     * Creates a new ResponseEntity with 
     * the given response body object, the  
     * response headers and the http status code. 
     * <p>
     * If no {@link ResponseBodySerializerStrategy} is given
     * the default JSON response body serializer will be assumed.
     *  
     * @param body            The response body object
     * @param headers         The response headers
     * @param httpStatusCode  The http status code
     * @param serializer      The response body serializer
     * 
     * @return responseEntity The response entity object
     */
    public static ResponseEntity of(Object entity, 
            Map<String, String> headers, 
            int httpStatusCode,             
            ResponseBodySerializerStrategy serializer) {
        
        String json = null;     
        if (entity != null)
            json = serializeEntityAttributes(entity, serializer);
            
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatusCode(httpStatusCode);
        responseEntity.setHeaders(headers);     
        responseEntity.setBody(json);       
                
        return responseEntity;
    }
    
    /**
     * Serializes the response body object
     * <p>
     * If no response body serializer strategy is given 
     * the default JSON serializer will be assumed.
     * 
     * @param entity     The response body object
     * @param serializer The response body serializer
     * @return responseEntity The response entity object
     * @throws JsonProcessingException 
     */
    private static String serializeEntityAttributes(Object entity, ResponseBodySerializerStrategy serializer) {
        String json = null;
        
        try {
            
            if (serializer != null)
                return serializer.serialize(entity);
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            json = mapper.writeValueAsString(entity);   
            
        } catch (JsonProcessingException e) {           
            throw new RuntimeException(e);
            
        } catch (HttpException e) {
            throw new RuntimeException(e);
        }
        
        return json;
    }
    
    /**
     * Returns the serialized response body
     * 
     * @return body The serialized response body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the serialized response body
     * 
     * @param body The serialized response body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Returns the response headers
     * 
     * @return headers The response headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets the response headers
     * 
     * @param headers The response headers
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Returns the http status code
     * 
     * @return statusCode The http status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the http status code
     * 
     * @param statusCode The http status code
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
}
