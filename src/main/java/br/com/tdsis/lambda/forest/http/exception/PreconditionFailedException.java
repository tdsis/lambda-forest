package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The PreconditionFailedException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a precondition failed http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_PRECONDITION_FAILED}
 */
public class PreconditionFailedException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_PRECONDITION_FAILED;

    public PreconditionFailedException() {
        super(HTTP_STATUS);
    }

    public PreconditionFailedException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public PreconditionFailedException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public PreconditionFailedException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
