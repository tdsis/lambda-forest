package br.com.tdsis.lambda.forest.auth;

import com.amazonaws.services.lambda.runtime.Context;

import br.com.tdsis.lambda.forest.auth.domain.AuthPolicy;
import br.com.tdsis.lambda.forest.auth.domain.AuthRequest;
import br.com.tdsis.lambda.forest.http.exception.HttpException;

/**
 * The APIGatewayAuthorizer interface.
 * 
 * <p>
 * This interface represents a definition for a custom API Gateway Authorizer.
 * 
 * @author nmelo 
 */
public interface APIGatewayAuthorizer {

    /**
     * Performs a custom API Gateway Authorization.
     * 
     * @param request The request for authorize
     * @param context The Lambda Context
     * @return authPolicy The AuthPolicy (IAM Policy representation)
     * @throws HttpException exception The http exception
     */
    AuthPolicy authorize(AuthRequest request, Context context) throws HttpException;
    
}
