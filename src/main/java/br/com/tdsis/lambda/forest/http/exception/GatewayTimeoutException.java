package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The GatewayTimeoutException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a gateway timeout http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_GATEWAY_TIMEOUT}
 */
public class GatewayTimeoutException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_GATEWAY_TIMEOUT;

    public GatewayTimeoutException() {
        super(HTTP_STATUS);
    }

    public GatewayTimeoutException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public GatewayTimeoutException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public GatewayTimeoutException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
