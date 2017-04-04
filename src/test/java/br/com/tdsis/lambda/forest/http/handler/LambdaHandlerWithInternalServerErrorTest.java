package br.com.tdsis.lambda.forest.http.handler;

import com.amazonaws.services.lambda.runtime.Context;

import br.com.tdsis.lambda.forest.http.exception.HttpException;

public class LambdaHandlerWithInternalServerErrorTest extends AbstractRequestHandler<UserRequestTest, UserResponseTest> {

    @Override
    public void before(Context context) throws HttpException {
        
    }
    
    @Override
    @SuppressWarnings("null")
    public UserResponseTest execute(UserRequestTest input, Context context) throws HttpException {
        // simulate null pointer
        // should handle the error and throw an internal server error
        String nullpointer = null;      
        nullpointer.concat("null pointer");
        
        return null;
    }

}
