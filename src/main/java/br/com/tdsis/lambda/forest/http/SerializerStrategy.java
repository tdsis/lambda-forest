package br.com.tdsis.lambda.forest.http;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.http.entity.ContentType;

import br.com.tdsis.lambda.forest.http.handler.AbstractRequestHandler;
import br.com.tdsis.lambda.forest.json.JsonResponseBodySerializerStrategy;

/**
 * The SerializerStrategy enum
 * <p>
 * It contains all the strategies to serialize a response body.
 * <br>
 * The current supported serializers are:
 * <ul>
 * <li>{@code JsonResponseBodySerializerStrategy} for application/json</li>
 * </ul>
 * <p>
 * If a specific serializer is not registered in this enum the 
 * {@link AbstractRequestHandler#resolveSerializerStrategy} should be overridden.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public enum SerializerStrategy {

    /**
     * The response body serializer for application/json
     */
    APPLICATION_JSON(ContentType.APPLICATION_JSON.getMimeType(), new JsonResponseBodySerializerStrategy());
        
    
    private String contentType;
    private ResponseBodySerializerStrategy serializer;
    
    /**
     * The default constructor
     * 
     * @param contentType The content type
     * @param deserializer The serializer strategy
     */
    SerializerStrategy(String contentType, ResponseBodySerializerStrategy serializer) {
        this.contentType = contentType;
        this.serializer = serializer;
    }
    
    /**
     * Returns the serializer strategy
     * 
     * @param contentType The content type
     * @return optional An optional of a serializer strategy
     */
    public static Optional<SerializerStrategy> strategy(String contentType) {
        return Stream.of(SerializerStrategy.values())
            .filter(e -> e.getContentType().equals(contentType))
            .findFirst();
    }
    
    /**
     * Returns the content type associated 
     * with this deserializer strategy
     * 
     * @return contentType The content type
     */
    public String getContentType() {
        return contentType;
    }
    
    /**
     * Returns the response body serializer strategy
     *  
     * @return serializer The response body serializer
     */
    public ResponseBodySerializerStrategy getResponseBodySerializer() {
        return serializer;
    }

    
}
