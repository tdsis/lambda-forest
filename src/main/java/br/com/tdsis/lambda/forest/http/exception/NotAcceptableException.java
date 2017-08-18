package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The NotAcceptableException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a not acceptable http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_NOT_ACCEPTABLE}
 */
public class NotAcceptableException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_NOT_ACCEPTABLE;

    public NotAcceptableException() {
        super(HTTP_STATUS);
    }

    public NotAcceptableException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public NotAcceptableException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public NotAcceptableException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
