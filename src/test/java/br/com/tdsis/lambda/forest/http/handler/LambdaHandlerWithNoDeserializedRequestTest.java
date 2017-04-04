package br.com.tdsis.lambda.forest.http.handler;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.exception.InternalServerErrorException;
import br.com.tdsis.lambda.forest.http.request.HttpRequest;
import br.com.tdsis.lambda.forest.http.response.ResponseEntity;

public class LambdaHandlerWithNoDeserializedRequestTest extends AbstractRequestHandler<HttpRequest, ResponseEntity> {

    @Override
    public void before(Context context) throws HttpException {
        
    }

    @Override
    public ResponseEntity execute(HttpRequest input, Context context) throws HttpException {
        UserRequestTest user = null;
        try {
            
            user = new ObjectMapper().readValue(input.getBody(), UserRequestTest.class);
            
        } catch (IOException e) {
            throw new InternalServerErrorException(e.getMessage(), e.getCause());
        }
        
        return ResponseEntity.of(user);
    }

}
