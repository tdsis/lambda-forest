package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The UriTooLongException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a method not allowed http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_REQUEST_URI_TOO_LONG}
 */
public class UriTooLongException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_REQUEST_URI_TOO_LONG;

    public UriTooLongException() {
        super(HTTP_STATUS);
    }

    public UriTooLongException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public UriTooLongException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public UriTooLongException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
