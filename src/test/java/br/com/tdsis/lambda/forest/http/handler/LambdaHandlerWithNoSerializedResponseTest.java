package br.com.tdsis.lambda.forest.http.handler;

import com.amazonaws.services.lambda.runtime.Context;

import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.response.ResponseEntity;

public class LambdaHandlerWithNoSerializedResponseTest extends AbstractRequestHandler<UserRequestTest, ResponseEntity> {

    @Override
    public void before(Context context) throws HttpException {
        
        
    }

    @Override
    public ResponseEntity execute(UserRequestTest input, Context context) throws HttpException {
        return ResponseEntity.of(input);
    }

    

}
