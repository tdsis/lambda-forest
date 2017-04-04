package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The BadRequestException class
 * <p>
 * This is a concrete class of the the HttpException class.
 * It represents a bad request http response.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link HttpStatus#SC_BAD_REQUEST}
 */
public class BadRequestException extends HttpException {

    private static final long serialVersionUID = -4425605776581035356L;
        
    public BadRequestException() {      
        super(HttpStatus.SC_BAD_REQUEST);
    }
        
    public BadRequestException(Object entity) {     
        super(entity, HttpStatus.SC_BAD_REQUEST);
    }

    public BadRequestException(final String message, final Throwable cause) {       
        super(HttpStatus.SC_BAD_REQUEST, message, cause);
    }
        
    public BadRequestException(Object entity, final String message, final Throwable cause) {        
        super(entity, HttpStatus.SC_BAD_REQUEST, message, cause);
    }

}
