package br.com.tdsis.lambda.forest.xml;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.tdsis.lambda.forest.domain.DefaultResponseError;
import br.com.tdsis.lambda.forest.http.RequestBodyDeserializerStrategy;
import br.com.tdsis.lambda.forest.http.exception.BadRequestException;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.exception.InternalServerErrorException;

/**
 * The XML request body deserializer strategy
 * 
 * @author nmelo
 */
public class XmlRequestBodyDeserializerStrategy implements RequestBodyDeserializerStrategy {
    
    public static final String INVALID_XML_MESSAGE = "Invalid XML";   
    public static final String INVALID_XML_ATTRIBUTE = "Invalid XML attribute: %s";
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(String body, Class<?> entityClass) throws HttpException {
        XmlMapper mapper = new XmlMapper();
        
         try {

            return (T) mapper.readValue(body, entityClass);
            
        } catch (JsonParseException e) {
            throw new BadRequestException(new DefaultResponseError(INVALID_XML_MESSAGE));  

        } catch (JsonMappingException e) {
            String message = INVALID_XML_MESSAGE;
            
            if (e instanceof UnrecognizedPropertyException){                
                message = String.format(INVALID_XML_ATTRIBUTE, 
                        ((UnrecognizedPropertyException) e).getPropertyName());
            }
                
            throw new BadRequestException(new DefaultResponseError(message));               
            
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }
                
    }

}
