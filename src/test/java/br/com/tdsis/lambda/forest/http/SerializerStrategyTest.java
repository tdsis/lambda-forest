package br.com.tdsis.lambda.forest.http;

import java.util.Optional;

import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import br.com.tdsis.lambda.forest.json.JsonResponseBodySerializerStrategy;

public class SerializerStrategyTest {

    @Test
    public void jsonStrategyShouldBeNotNull() {
        Assert.assertNotNull(SerializerStrategy.APPLICATION_JSON);
    }
    
    @Test
    public void unknownStrategyShouldBeNull() {
        Optional<SerializerStrategy> optional = SerializerStrategy.strategy("unknown/unknown");
        Assert.assertFalse(optional.isPresent());
    }
    
    public void jsonStrategyShouldBeOk() {      
        Optional<SerializerStrategy> optional = SerializerStrategy
                .strategy(ContentType.APPLICATION_JSON.getMimeType());
        
        Assert.assertTrue(optional.isPresent());
        SerializerStrategy strategy = optional
                .orElseThrow(() -> new RuntimeException("Accept type Serializer not implemented yet"));
        
        Assert.assertEquals(ContentType.APPLICATION_JSON.getMimeType(), strategy.getContentType());
        ResponseBodySerializerStrategy deserializer = strategy.getResponseBodySerializer();
        
        Assert.assertNotNull(deserializer);
        Assert.assertTrue(deserializer instanceof JsonResponseBodySerializerStrategy);
    }
    
}
