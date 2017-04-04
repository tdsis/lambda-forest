package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The UnauthorizedException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents an unauthorized http response.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link HttpStatus#SC_UNAUTHORIZED}
 */
public class UnauthorizedException extends HttpException {
    
    private static final long serialVersionUID = 1122175739094587378L;
    
    public UnauthorizedException() {        
        super(HttpStatus.SC_UNAUTHORIZED);
    }
    
    public UnauthorizedException(Object entity) {       
        super(entity, HttpStatus.SC_UNAUTHORIZED);
    }

    public UnauthorizedException(final String message, final Throwable cause) {     
        super(HttpStatus.SC_UNAUTHORIZED, message, cause);
    }
    
    public UnauthorizedException(Object entity, final String message, final Throwable cause) {      
        super(entity, HttpStatus.SC_UNAUTHORIZED, message, cause);
    }

}
