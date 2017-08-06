package br.com.tdsis.lambda.forest.auth;

import com.amazonaws.services.lambda.runtime.Context;

import br.com.tdsis.lambda.forest.auth.domain.AuthPolicy;
import br.com.tdsis.lambda.forest.auth.domain.AuthPolicyBuilder;
import br.com.tdsis.lambda.forest.auth.domain.AuthRequest;
import br.com.tdsis.lambda.forest.auth.domain.PolicyStatement;
import br.com.tdsis.lambda.forest.auth.handler.AbstractAPIGatewayAuthorizer;
import br.com.tdsis.lambda.forest.http.exception.HttpException;

public class CustomAPIGatewayAuthorizerTest extends AbstractAPIGatewayAuthorizer {

    @Override
    public AuthPolicy authorize(AuthRequest request, Context context) throws HttpException {
        PolicyStatement statement = new PolicyStatement(
                AuthPolicy.ACTION_INVOKE, 
                AuthPolicy.ALLOW, 
                request.getMethodArn());
        
        return new AuthPolicyBuilder("123456")
                .addPolicyStatement(statement)
                .addToContext("customKey", "customValue")
                .build();
    }

}
