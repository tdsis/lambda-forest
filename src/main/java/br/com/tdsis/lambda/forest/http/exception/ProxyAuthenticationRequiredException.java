package br.com.tdsis.lambda.forest.http.exception;

import org.apache.http.HttpStatus;

/**
 * The ProxyAuthenticationRequiredException class
 * <p>
 * This is a concrete class of the the HttpException class. 
 * It represents a proxy authentication required http response.
 * 
 * @author fsantana
 * @version 0.0.2
 * @since 0.0.2
 * @see {@link HttpStatus#SC_PROXY_AUTHENTICATION_REQUIRED}
 */
public class ProxyAuthenticationRequiredException extends HttpException {

    private static final long serialVersionUID = 3099405537692347007L;
    private static final int HTTP_STATUS = HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED;

    public ProxyAuthenticationRequiredException() {
        super(HTTP_STATUS);
    }

    public ProxyAuthenticationRequiredException(Object entity) {
        super(entity, HTTP_STATUS);
    }

    public ProxyAuthenticationRequiredException(final String message, final Throwable cause) {
        super(HTTP_STATUS, message, cause);
    }

    public ProxyAuthenticationRequiredException(Object entity, final String message, final Throwable cause) {
        super(entity, HTTP_STATUS, message, cause);
    }

}
