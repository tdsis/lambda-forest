package br.com.tdsis.lambda.forest.xml;

import org.junit.Assert;
import org.junit.Test;

import br.com.tdsis.lambda.forest.http.exception.BadRequestException;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.exception.InternalServerErrorException;
import br.com.tdsis.lambda.forest.http.handler.UserRequestTest;
import br.com.tdsis.lambda.forest.json.JsonRequestBodyDeserializerStrategy;

public class XmlRequestBodyDeserializerStrategyTest {

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestException() throws HttpException {
        XmlRequestBodyDeserializerStrategy deserializer = new XmlRequestBodyDeserializerStrategy();
        deserializer.deserialize("<any>any</any>", UserRequestTest.class);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestExceptionWithUnrecognizedAttribute() throws HttpException {
        XmlRequestBodyDeserializerStrategy deserializer = new XmlRequestBodyDeserializerStrategy();
        deserializer.deserialize("<any>any</any>", UserRequestTest.class);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestExceptionWithUnknownAttribute() throws HttpException {
        JsonRequestBodyDeserializerStrategy deserializer = new JsonRequestBodyDeserializerStrategy();
        deserializer.deserialize("<unknown>any</unknown>", UserRequestTest.class);
    }

    @Test(expected = InternalServerErrorException.class)
    public void shouldThrowInternalServerErrorException() throws HttpException {
        XmlRequestBodyDeserializerStrategy deserializer = new XmlRequestBodyDeserializerStrategy();
        deserializer.deserialize(null, UserRequestTest.class);
    }
    
    @Test
    public void shouldDeserializeBean() throws HttpException {
        XmlRequestBodyDeserializerStrategy deserializer = new XmlRequestBodyDeserializerStrategy();
        UserRequestTest bean = deserializer
                .deserialize("<UserRequestTest><name>Any Name</name><address>Any Address</address></UserRequestTest>", 
                        UserRequestTest.class);
        
        Assert.assertNotNull(bean);
        Assert.assertEquals("Any Name", bean.getName());
        Assert.assertEquals("Any Address", bean.getAddress());
    }

    
}
