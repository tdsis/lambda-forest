package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The InternalServerErrorException class
 * <p>
 * This is a concrete class of the the HttpException class.
 * It represents an internal server error http response.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link HttpStatus#SC_INTERNAL_SERVER_ERROR}
 */
public class InternalServerErrorException extends HttpException {

    private static final long serialVersionUID = -342863090082176929L;
    
    public InternalServerErrorException() {     
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(Object entity) {        
        super(entity, HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(final String message, final Throwable cause) {      
        super(HttpStatus.SC_INTERNAL_SERVER_ERROR, message, cause);
    }
    
    public InternalServerErrorException(Object entity, final String message, final Throwable cause) {       
        super(entity, HttpStatus.SC_INTERNAL_SERVER_ERROR, message, cause);
    }
    
}
