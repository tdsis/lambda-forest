package br.com.tdsis.lambda.forest.domain;

/**
 * The DefaultResponseError class
 * <p>
 * This class represents a response body when 
 * an InternalServerError occurs.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultResponseError {

    private String message;

    /**
     * The default constructor
     */
    public DefaultResponseError() {}
    
    /**
     * The constructor with the error message arg
     * 
     * @param message The error message
     */
    public DefaultResponseError(String message) {
        this.message = message;
    }
    
    /**
     * Returns the error message
     * 
     * @return message The error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message
     * 
     * @param message The error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
