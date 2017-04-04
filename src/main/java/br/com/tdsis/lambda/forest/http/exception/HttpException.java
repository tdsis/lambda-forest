package br.com.tdsis.lambda.forest.http.exception;

/**
 * The HttpException class
 * <p>
 * This is the abstract base class for all the http exception representations.
 * <br>
 * All the HttpException classes have two attributes that are used 
 * to create a http response:
 * <ul>
 * <li>httpStatusCode - The http status code</li>
 * <li>entity - The object that represents the response body</li>
 * </ul>  
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class HttpException extends Exception {

    private static final long serialVersionUID = -2384366951143588679L;
    
    private int httpStatusCode;
    private Object entity;

    /**
     * The constructor with a http status code parameter
     * 
     * @param httpStatusCode The http status code
     */
    public HttpException(int httpStatusCode) {
        super();            
        this.httpStatusCode = httpStatusCode;       
    }
    
    /**
     * The constructor with an object that represents 
     * the response body and a http status code
     * 
     * @param entity         The object that represents the response body
     * @param httpStatusCode The http status code
     */
    public HttpException(Object entity, int httpStatusCode) {
        super();    
        this.entity = entity;
        this.httpStatusCode = httpStatusCode;       
    }
            
    /**
     * The constructor with a http status code, 
     * an error message and an error cause
     * 
     * @param httpStatusCode The http status code
     * @param message        The error message
     * @param cause          The error cause
     */
    public HttpException(int httpStatusCode, final String message, final Throwable cause) {
        super(message);
        initCause(cause);                
        this.httpStatusCode = httpStatusCode;
    }
    
    /**
     * The constructor with an object that represents 
     * the response body, a http status code, an error message 
     * and an error cause.
     * 
     * @param entity         The object that represents the response body
     * @param httpStatusCode The http status code
     * @param message        The error message
     * @param cause          The error cause
     */
    public HttpException(Object entity, int httpStatusCode, final String message, final Throwable cause) {
        super(message);
        initCause(cause);        
        this.entity = entity;
        this.httpStatusCode = httpStatusCode;
    }   
    
    /**
     * Returns the http status code
     * 
     * @return httpStatusCode The http status code
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * Sets the http status code
     * 
     * @param httpStatusCode The http status code
     */
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * Returns the entity that represents the response body
     * 
     * @return entity The entity
     */
    public Object getEntity() {
        return entity;
    }

    /**
     * Sets the entity that represents the response body
     * 
     * @param entity The entity
     */
    public void setEntity(Object entity) {
        this.entity = entity;
    }

}
