package br.com.tdsis.lambda.forest.http.exception;

/**
 * The TooManyRequestsException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a too many requests http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 */
public class TooManyRequestsException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = 429;

    public TooManyRequestsException() {
        super(HTTP_STATUS);
    }

    public TooManyRequestsException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public TooManyRequestsException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public TooManyRequestsException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
