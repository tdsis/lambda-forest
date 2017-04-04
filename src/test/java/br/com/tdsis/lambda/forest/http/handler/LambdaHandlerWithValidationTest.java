package br.com.tdsis.lambda.forest.http.handler;

import javax.validation.Valid;

import com.amazonaws.services.lambda.runtime.Context;

import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.handler.AbstractRequestHandler;

public class LambdaHandlerWithValidationTest extends AbstractRequestHandler<UserRequestTest, UserResponseTest> {

    @Override
    public void before(Context context) throws HttpException {
        
    }

    @Override
    public UserResponseTest execute(@Valid UserRequestTest input, Context context) throws HttpException {

        return null;
    }

}
