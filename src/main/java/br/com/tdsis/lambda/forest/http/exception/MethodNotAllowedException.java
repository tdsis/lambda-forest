package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The MethodNotAllowedException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a method not allowed http response.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link HttpStatus#SC_METHOD_NOT_ALLOWED}
 */
public class MethodNotAllowedException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;

    public MethodNotAllowedException() {
        super(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    public MethodNotAllowedException(Object entity) {
        super(entity, HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    public MethodNotAllowedException(final String message, final Throwable cause) {
        super(HttpStatus.SC_METHOD_NOT_ALLOWED, message, cause);
    }

    public MethodNotAllowedException(Object entity, final String message, final Throwable cause) {
        super(entity, HttpStatus.SC_METHOD_NOT_ALLOWED, message, cause);
    }

}
