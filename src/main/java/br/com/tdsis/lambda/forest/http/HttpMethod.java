package br.com.tdsis.lambda.forest.http;

/**
 * The HttpMethod enum
 * <p>
 * It contains the default http method names
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public enum HttpMethod {

    /**
     * The http POST method
     */
    POST("POST"),
    
    /**
     * The http GET method
     */
    GET("GET"),
    
    /**
     * The http PUT method
     */
    PUT("PUT"),
    
    /**
     * The http PATCH method
     */ 
    PATCH("PATCH"),
    
    /**
     * The http DELETE method
     */
    DELETE("DELETE"),
    
    /**
     * The http OPTIONS method
     */
    OPTIONS("OPTIONS"),
    
    /**
     * The http HEAD method
     */
    HEAD("HEAD"),
    
    /**
     * The http TRACE method
     */
    TRACE("TRACE"),
    
    /**
     * The http CONNECT method
     */
    CONNECT("CONNECT"); 
    
    private String method;
    
    /**
     * The default constructor
     * 
     * @param method The http method name
     */
    HttpMethod(String method){
        this.method = method;
    }
    
    /**
     * Returns the http method name
     * 
     * @return method The http method name
     */
    public String getMethod() {
        return method;
    }
}
