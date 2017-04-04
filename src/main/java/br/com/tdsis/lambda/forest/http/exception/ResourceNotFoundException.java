package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The ResourceNotFoundException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a resource not found http response.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link HttpStatus#SC_NOT_FOUND}
 */
public class ResourceNotFoundException extends HttpException {

    private static final long serialVersionUID = -716240881147407901L;
    
    public ResourceNotFoundException() {        
        super(HttpStatus.SC_NOT_FOUND);
    }
        
    public ResourceNotFoundException(Object entity) {       
        super(entity, HttpStatus.SC_NOT_FOUND);
    }
    
    public ResourceNotFoundException(final String message, final Throwable cause) {     
        super(HttpStatus.SC_NOT_FOUND, message, cause);
    }

    public ResourceNotFoundException(Object entity, final String message, final Throwable cause) {      
        super(entity, HttpStatus.SC_NOT_FOUND, message, cause);
    }

}
