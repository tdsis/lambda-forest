package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The ConflictException class
 * <p>
 * This is a concrete class of the the HttpException class.
 * It represents a conflict http response.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link HttpStatus#SC_CONFLICT}
 */
public class ConflictException extends HttpException {

    private static final long serialVersionUID = 7075542181833160485L;
        
    public ConflictException() {        
        super(HttpStatus.SC_CONFLICT);
    }
        
    public ConflictException(Object entity) {       
        super(entity, HttpStatus.SC_CONFLICT);
    }

    public ConflictException(final String message, final Throwable cause) {     
        super(HttpStatus.SC_CONFLICT, message, cause);
    }
        
    public ConflictException(Object entity, final String message, final Throwable cause) {      
        super(entity, HttpStatus.SC_CONFLICT, message, cause);
    }
    
}
