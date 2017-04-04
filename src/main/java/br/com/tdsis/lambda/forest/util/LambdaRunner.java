package br.com.tdsis.lambda.forest.util;

import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.tdsis.lambda.forest.domain.LambdaRequestSpec;
import br.com.tdsis.lambda.forest.domain.LambdaSpec;
import br.com.tdsis.lambda.forest.http.handler.AbstractRequestHandler;
import br.com.tdsis.lambda.forest.http.request.HttpRequest;
import br.com.tdsis.lambda.forest.http.response.ResponseEntity;

/**
 * The Lambda runner utility class
 * <p>
 * This class provides methods to simulate a Lambda execution call
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public class LambdaRunner {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    static {
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    }
    
    /**
     * Simulates a Lambda execution call and prints the output result
     * 
     * @param spec   The Lambda execution specification file name
     * @param runner The runner class
     * @param args   The args
     */
    public static void run(String spec, 
            Class<? extends AbstractRequestHandler<?, ?>> runner,
            String [] args) {
        
        try {
                                   
            ClassLoader loader = runner.getClassLoader();
            InputStream input = loader.getResourceAsStream(spec);                   
            
            LambdaSpec lambdaSpec = MAPPER.readValue(input, LambdaSpec.class);          
            LambdaRequestSpec requestSpec = lambdaSpec.getRequest();                                                                       
            HttpRequest request = buildHttpRequest(requestSpec);                                       
               
            AbstractRequestHandler<?, ?> handler = runner.newInstance();
            ResponseEntity response = handler.handleRequest(request, lambdaSpec.getContext());
            
            System.out.println(MAPPER.writeValueAsString(response));
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    
    
    /**
     * Builds a http request with the given request specification
     * 
     * @param requestSpec The request specification
     * @return httpRequest The http request to be performed
     * 
     * @throws JsonProcessingException 
     */
    private static HttpRequest buildHttpRequest(LambdaRequestSpec requestSpec) throws JsonProcessingException {
        HttpRequest request = new HttpRequest();
        
        String resource = requestSpec.getResource();
        String path = requestSpec.getPath();                
        String method = requestSpec.getMethod();                
        String json = MAPPER.writeValueAsString(requestSpec.getBody());
        Map<String, String> headers = requestSpec.getHeaders();
        Map<String, String> pathParameters = requestSpec.getPathParameters();
        Map<String, String> queryStringParameters = requestSpec.getQueryStringParameters();
        Map<String, String> stageVariables = requestSpec.getStageVariables();
        
        request.setResource(resource);
        request.setPath(path);              
        request.setHttpMethod(method);
        request.setHeaders(headers);
        request.setBody(json);
        request.setPathParameters(pathParameters);
        request.setQueryStringParameters(queryStringParameters);
        request.setStageVariables(stageVariables);
        
        return request;
    }
    
}
