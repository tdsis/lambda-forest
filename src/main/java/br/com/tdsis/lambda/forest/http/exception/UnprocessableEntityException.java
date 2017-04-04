package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The UnprocessableEntityException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents an unprocessable entity http response.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link HttpStatus#SC_UNPROCESSABLE_ENTITY}
 */
public class UnprocessableEntityException extends HttpException {

    private static final long serialVersionUID = -1023599649444536547L;
        
    public UnprocessableEntityException() {     
        super(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }
        
    public UnprocessableEntityException(Object entity) {        
        super(entity, HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }
    
    public UnprocessableEntityException(final String message, final Throwable cause) {      
        super(HttpStatus.SC_UNPROCESSABLE_ENTITY, message, cause);
    }
    
    public UnprocessableEntityException(Object entity, final String message, final Throwable cause) {       
        super(entity, HttpStatus.SC_UNPROCESSABLE_ENTITY, message, cause);
    }
    
}
