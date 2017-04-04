
package br.com.tdsis.lambda.forest.http.request;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HttpRequest class
 * <p>
 * It represents a resource mapping of the request given by  
 * the AWS API Gateway when using integration proxy.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see <a href="http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-set-up-simple-proxy.html">
 *          http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-set-up-simple-proxy.html</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpRequest {

    @JsonProperty("body")
    private String body;
    
    @JsonProperty("resource")
    private String resource;
       
    @JsonProperty("queryStringParameters")
    private Map<String, String> queryStringParameters;
    
    @JsonProperty("headers")
    private Map<String, String> headers;
    
    @JsonProperty("pathParameters")
    private Map<String, String> pathParameters;
    
    @JsonProperty("httpMethod")
    private String httpMethod;
    
    @JsonProperty("stageVariables")
    private Map<String, String> stageVariables;
    
    @JsonProperty("path")
    private String path;
    
    @JsonProperty("isBase64Encoded")
    private boolean base64Encoded; 
    
    @JsonIgnore
    private Map<String, String> additionalProperties = new HashMap<String, String>();

    /**
     * Returns the raw body request
     * 
     * @return body
     */    
    public String getBody() {
        return body;
    }

    /**
     * Sets the raw body request
     * 
     * @param body The raw body request
     */    
    public void setBody(String body) {
        this.body = body;
    }

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
     * @param queryStringParameters The queryStringParameters
     */
    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    /**
     * Returns the headers
     * 
     * @return headers The headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets the headers
     * 
     * @param headers The headers
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
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
     * Returns the http method
     * 
     * @return httpMethod The http method
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * Sets the http method
     * 
     * @param httpMethod The httpMethod
     */
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
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
     * @param stageVariables The stageVariables
     */
    public void setStageVariables(Map<String, String> stageVariables) {
        this.stageVariables = stageVariables;
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

    /**
     * Returns the additional properties
     * 
     * @return additionalProperties The additional properties
     */
    @JsonAnyGetter
    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Sets a additional property
     * 
     * @param name The property key
     * @param value The property value
     */
    @JsonAnySetter
    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }       
    
}
