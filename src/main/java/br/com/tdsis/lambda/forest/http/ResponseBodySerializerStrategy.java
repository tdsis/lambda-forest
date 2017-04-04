package br.com.tdsis.lambda.forest.http;

import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.json.JsonResponseBodySerializerStrategy;

/**
 * The ResponseBodySerializerStrategy interface.
 * <p>
 * It defines a contract to be implemented by a response body serializer.
 *
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * 
 * @see {@link JsonResponseBodySerializerStrategy}
 */
public interface ResponseBodySerializerStrategy {

    /**
     * Serializes an object
     * 
     * @param entity The object to be serialized
     * @return serializedObject The string representation of the object
     * 
     * @throws HttpException Throws an HttpException if the serialization fails
     */
    String serialize(Object entity) throws HttpException;
    
}
