package br.com.tdsis.lambda.forest.auth;

import com.amazonaws.services.lambda.runtime.Context;

import br.com.tdsis.lambda.forest.auth.domain.AuthPolicy;
import br.com.tdsis.lambda.forest.auth.domain.AuthRequest;
import br.com.tdsis.lambda.forest.auth.handler.AbstractAPIGatewayAuthorizer;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.exception.UnauthorizedException;

public class AuthorizerWithHttpExceptionTest extends AbstractAPIGatewayAuthorizer {

    @Override
    public AuthPolicy authorize(AuthRequest request, Context context) throws HttpException {		
        throw new UnauthorizedException();
    }

}
