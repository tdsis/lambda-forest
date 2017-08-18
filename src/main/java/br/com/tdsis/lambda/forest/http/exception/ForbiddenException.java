package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The ForbiddenException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a forbidden http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_FORBIDDEN}
 */
public class ForbiddenException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_FORBIDDEN;

    public ForbiddenException() {
        super(HTTP_STATUS);
    }

    public ForbiddenException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public ForbiddenException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public ForbiddenException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
