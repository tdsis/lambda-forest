package br.com.tdsis.lambda.forest.http;

import java.util.Optional;

import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import br.com.tdsis.lambda.forest.json.JsonRequestBodyDeserializerStrategy;

public class DeserializerStrategyTest {

    @Test
    public void jsonStrategyShouldBeNotNull() {
        Assert.assertNotNull(DeserializerStrategy.APPLICATION_JSON);
    }
    
    @Test
    public void unknownStrategyShouldBeNull() {
        Optional<DeserializerStrategy> optional = DeserializerStrategy.strategy("unknown/unknown");
        Assert.assertFalse(optional.isPresent());
    }
    
    public void jsonStrategyShouldBeOk() {      
        Optional<DeserializerStrategy> optional = DeserializerStrategy
                .strategy(ContentType.APPLICATION_JSON.getMimeType());
        
        Assert.assertTrue(optional.isPresent());
        DeserializerStrategy strategy = optional
                .orElseThrow(() -> new RuntimeException("Content type deserializer not implemented yet"));
        
        Assert.assertEquals(ContentType.APPLICATION_JSON.getMimeType(), strategy.getContentType());
        RequestBodyDeserializerStrategy deserializer = strategy.getRequestBodyDeserializer();
        
        Assert.assertNotNull(deserializer);
        Assert.assertTrue(deserializer instanceof JsonRequestBodyDeserializerStrategy);
    }
    
    
    
}
