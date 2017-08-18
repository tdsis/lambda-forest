package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The RequestedRangeNotSatisfiableException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a requested range not satisfiable http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_REQUESTED_RANGE_NOT_SATISFIABLE}
 */
public class RequestedRangeNotSatisfiableException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE;

    public RequestedRangeNotSatisfiableException() {
        super(HTTP_STATUS);
    }

    public RequestedRangeNotSatisfiableException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public RequestedRangeNotSatisfiableException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public RequestedRangeNotSatisfiableException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
