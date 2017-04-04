package br.com.tdsis.lambda.forest.http;

import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.json.JsonRequestBodyDeserializerStrategy;

/**
 * The RequestBodyDeserializerStrategy interface.
 * <p>
 * It defines a contract to be implemented by a request body deserializer.
 *
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * 
 * @see {@link JsonRequestBodyDeserializerStrategy}
 */
public interface RequestBodyDeserializerStrategy {

    /**
     * Deserializes a raw request body
     * 
     * @param body The raw request body
     * @param entityClass The class type to deserialize
     * @return T The deserialized object
     * 
     * @throws HttpException Throws an HttpException if the deserealization fails.
     */
    <T> T deserialize(String body, Class<?> entityClass) throws HttpException;
}
