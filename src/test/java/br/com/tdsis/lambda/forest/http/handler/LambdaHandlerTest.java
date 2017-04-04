package br.com.tdsis.lambda.forest.http.handler;

import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;

import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.handler.AbstractRequestHandler;

public class LambdaHandlerTest extends AbstractRequestHandler<UserRequestTest, UserResponseTest> {

    @Override
    public void before(Context context) throws HttpException {
        addResponseHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    public UserResponseTest execute(UserRequestTest input, Context context) throws HttpException {
                            
        UserResponseTest response = new UserResponseTest();
        response.setId(UUID.randomUUID().toString());
        response.setName(input.getName());
        response.setAddress(input.getAddress());
        
        return response;
    }

}
