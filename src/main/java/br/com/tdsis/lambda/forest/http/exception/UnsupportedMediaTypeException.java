package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The UnsupportedMediaTypeException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents an unsupported media type http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_UNSUPPORTED_MEDIA_TYPE}
 */
public class UnsupportedMediaTypeException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE;

    public UnsupportedMediaTypeException() {
        super(HTTP_STATUS);
    }

    public UnsupportedMediaTypeException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public UnsupportedMediaTypeException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public UnsupportedMediaTypeException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
