package br.com.tdsis.lambda.forest.xml;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.tdsis.lambda.forest.http.ResponseBodySerializerStrategy;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.exception.InternalServerErrorException;

/**
 * The XML response body serializer strategy
 * 
 * @author nmelo
 */
public class XmlResponseBodySerializerStrategy implements ResponseBodySerializerStrategy {

    @Override
    public String serialize(Object entity) throws HttpException {
        String xml = null;
        
        try {
            
            XmlMapper mapper = new XmlMapper();            
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            
            xml = mapper.writeValueAsString(entity);   
            
        } catch (JsonProcessingException e) {           
            throw new InternalServerErrorException(e.getMessage(), e);      
        }
        
        return xml;
    }

}
