package br.com.tdsis.lambda.forest.json;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.tdsis.lambda.forest.http.exception.BadRequestException;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.exception.InternalServerErrorException;
import br.com.tdsis.lambda.forest.http.handler.UserRequestTest;

public class JsonRequestBodyDeserializerStrategyTest {

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestException() throws HttpException {
        JsonRequestBodyDeserializerStrategy deserializer = new JsonRequestBodyDeserializerStrategy();
        deserializer.deserialize("{none: none}", UserRequestTest.class);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestExceptionWithUnrecognizedAttribute() throws HttpException {
        JsonRequestBodyDeserializerStrategy deserializer = new JsonRequestBodyDeserializerStrategy();
        deserializer.deserialize("{\"unrecognized\": \"any\"}", UserRequestTest.class);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestExceptionWithUnknownAttribute() throws HttpException {
        JsonRequestBodyDeserializerStrategy deserializer = new JsonRequestBodyDeserializerStrategy();
        deserializer.deserialize("{\"name\": \"any\"}", Pojo.class);
    }

    @Test(expected = InternalServerErrorException.class)
    public void shouldThrowInternalServerErrorException() throws HttpException {
        JsonRequestBodyDeserializerStrategy deserializer = new JsonRequestBodyDeserializerStrategy();
        deserializer.deserialize(null, UserRequestTest.class);
    }

    class Pojo {
        private String name;
        private String address;

        public Pojo() {

        }

        @JsonCreator
        public Pojo(@JsonProperty(required = true) String name, @JsonProperty(required = true) String address) {

            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }
}
