package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The ServiceUnavailableException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a service unavailable http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_SERVICE_UNAVAILABLE}
 */
public class ServiceUnavailableException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_SERVICE_UNAVAILABLE;

    public ServiceUnavailableException() {
        super(HTTP_STATUS);
    }

    public ServiceUnavailableException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public ServiceUnavailableException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public ServiceUnavailableException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
