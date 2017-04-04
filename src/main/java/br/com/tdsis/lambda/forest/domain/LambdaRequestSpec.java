package br.com.tdsis.lambda.forest.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.tdsis.lambda.forest.util.LambdaRunner;

/**
 * The LambdaRequestSpec class
 * <p>
 * This class represents a request to be 
 * handled by a lambda handler.
 * <p> 
 * It should be used only for local tests functionality
 * using {@code LambdaRunner}
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link LambdaRunner}
 */
public class LambdaRequestSpec {

    private String resource;
    private String path;
    private String method;
    private Map<String, String> headers;
    private Map<String, Object> body;
    private Map<String, String> pathParameters;
    private Map<String, String> queryStringParameters;
    private Map<String, String> stageVariables;
    
    @JsonProperty("isBase64Encoded")
    private boolean base64Encoded; 

    /**
     * Returns the resource
     * 
     * @return resource The resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the resource
     * 
     * @param resource The resource
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Returns the path
     * 
     * @return path The path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path
     * 
     * @param path The path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Returns the http method
     * 
     * @return method The http method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the http method
     * 
     * @param method The http method
     */
    public void setMethod(String method) {
        this.method = method;
    }
    
    /**
     * Returns the request headers
     * 
     * @return headers The request headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets the request headers
     * 
     * @param headers The request headers
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Returns the request body
     * 
     * @return body The request body
     */
    public Map<String, Object> getBody() {
        return body;
    }

    /**
     * Sets the request body
     * 
     * @param body The request body
     */
    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    /**
     * Returns the path parameters
     * 
     * @return pathParameters The path parameters
     */
    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    /**
     * Sets the path parameters
     * 
     * @param pathParameters The path parameters
     */
    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }
    
    /**
     * Returns the query string parameters
     * 
     * @return queryStringParameters The query string parameters
     */
    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }

    /**
     * Sets the query string parameters
     * 
     * @param queryStringParameters The query string parameters
     */
    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    /**
     * Returns the stage variables
     * 
     * @return stageVariables The stage variables
     */
    public Map<String, String> getStageVariables() {
        return stageVariables;
    }

    /**
     * Sets the stage variables
     * 
     * @param stageVariables The stage variables
     */
    public void setStageVariables(Map<String, String> stageVariables) {
        this.stageVariables = stageVariables;
    }

    
    /**
     * Returns base 64 encoded flag value
     * 
     * @return flag true for base 64 encoded, false otherwise
     */    
    public boolean isBase64Encoded() {
        return base64Encoded;
    }

    /**
     * Sets the base 64 encoded flag
     * 
     * @param base64Encoded The base 64 encoded flag
     */
    public void setBase64Encoded(boolean base64Encoded) {
        this.base64Encoded = base64Encoded;
    }
}
