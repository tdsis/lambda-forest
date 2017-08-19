package br.com.tdsis.lambda.forest.http;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.http.entity.ContentType;

import br.com.tdsis.lambda.forest.http.handler.AbstractRequestHandler;
import br.com.tdsis.lambda.forest.json.JsonRequestBodyDeserializerStrategy;
import br.com.tdsis.lambda.forest.xml.XmlRequestBodyDeserializerStrategy;

/**
 * The DeserializerStrategy enum
 * <p>
 * It contains all the strategies to deserialize a request body.
 * <br>
 * The current supported content type deserializers are:
 * <ul>
 * <li>{@code JsonRequestBodyDeserializerStrategy} for application/json</li>
 * <li>{@code XmlRequestBodyDeserializerStrategy} for text/xml</li>
 * </ul>
 * <p>
 * If a specific content type deserializer is not registered in this enum the 
 * {@link AbstractRequestHandler#resolveDeserializerStrategy} should be overridden.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DeserializerStrategy {

    /**
     * The request body deserializer for content type application/json
     */
    APPLICATION_JSON(ContentType.APPLICATION_JSON.getMimeType(), new JsonRequestBodyDeserializerStrategy()),
    
    /**
     * The request body deserializer for contente type text/xml
     */
    TEXT_XML(ContentType.TEXT_XML.getMimeType(), new XmlRequestBodyDeserializerStrategy());
        
    private String contentType;
    private RequestBodyDeserializerStrategy deserializer;
    
    /**
     * The default constructor
     * 
     * @param contentType The content type
     * @param deserializer The deserializer strategy
     */
    DeserializerStrategy(String contentType, RequestBodyDeserializerStrategy deserializer) {
        this.contentType = contentType;
        this.deserializer = deserializer;
    }
    
    /**
     * Returns the deserializer strategy
     * 
     * @param contentType The content type
     * @return optional An optional of a deserializer strategy
     */
    public static Optional<DeserializerStrategy> strategy(String contentType) {
        return Stream.of(DeserializerStrategy.values())
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
     * Returns the request body deserializer strategy
     *  
     * @return deserializer The request body deserializer
     */
    public RequestBodyDeserializerStrategy getRequestBodyDeserializer() {
        return deserializer;
    }
    
}
