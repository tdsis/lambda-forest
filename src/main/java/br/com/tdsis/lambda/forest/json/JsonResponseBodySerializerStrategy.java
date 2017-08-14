package br.com.tdsis.lambda.forest.json;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.tdsis.lambda.forest.http.ResponseBodySerializerStrategy;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.exception.InternalServerErrorException;

/**
 * The JSON response body serializer strategy
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public class JsonResponseBodySerializerStrategy implements ResponseBodySerializerStrategy {
        
    @Override
    public String serialize(Object entity) throws HttpException {
        String json = null;
        
        try {
            
            ObjectMapper mapper = new ObjectMapper();            
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            
            json = mapper.writeValueAsString(entity);   
            
        } catch (JsonProcessingException e) {           
            throw new InternalServerErrorException(e.getMessage(), e);      
        }
        
        return json;
    }

}
