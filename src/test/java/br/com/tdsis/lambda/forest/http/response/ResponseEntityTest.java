package br.com.tdsis.lambda.forest.http.response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdsis.lambda.forest.http.handler.UserRequestTest;
import br.com.tdsis.lambda.forest.json.JsonResponseBodySerializerStrategy;



public class ResponseEntityTest {

    @Test
    public void shouldSerializeSimpleResponseBody() throws JsonParseException, JsonMappingException, IOException {
        UserRequestTest request = new UserRequestTest("user name", "user address");
        ResponseEntity response = ResponseEntity.of(request);
        
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());        
        Assert.assertNotNull(response.getBody());
        
        //should be able to deserialize
        UserRequestTest deserialized = new ObjectMapper().readValue(response.getBody(), UserRequestTest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(request.getName(), deserialized.getName());
        Assert.assertEquals(request.getAddress(), deserialized.getAddress());
        
        Assert.assertNull(response.getHeaders());
    }
    
    
    @Test
    public void shouldOverwriteDefaultHttpStatusCode() throws JsonParseException, JsonMappingException, IOException {
        UserRequestTest request = new UserRequestTest("user name", "user address");
        ResponseEntity response = ResponseEntity.of(request, HttpStatus.SC_ACCEPTED);
        
        Assert.assertEquals(HttpStatus.SC_ACCEPTED, response.getStatusCode());      
        Assert.assertNotNull(response.getBody());
        
        //should be able to deserialize
        UserRequestTest deserialized = new ObjectMapper().readValue(response.getBody(), UserRequestTest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(request.getName(), deserialized.getName());
        Assert.assertEquals(request.getAddress(), deserialized.getAddress());
        
        Assert.assertNull(response.getHeaders());
    }
    
    
    @Test
    public void shouldReturnResponseHeaders() throws JsonParseException, JsonMappingException, IOException {
        UserRequestTest request = new UserRequestTest("user name", "user address");
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        
        ResponseEntity response = ResponseEntity.of(request, headers);
        
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());        
        Assert.assertNotNull(response.getBody());
        
        Map<String, String> responseHeaders = response.getHeaders();
        
        Assert.assertNotNull(responseHeaders);
        Assert.assertEquals("*", responseHeaders.get("Access-Control-Allow-Origin"));
        Assert.assertEquals(ContentType.APPLICATION_JSON.getMimeType(), 
                responseHeaders.get(HttpHeaders.CONTENT_TYPE));
        
        //should be able to deserialize
        UserRequestTest deserialized = new ObjectMapper().readValue(response.getBody(), UserRequestTest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(request.getName(), deserialized.getName());
        Assert.assertEquals(request.getAddress(), deserialized.getAddress());
    }
    
    @Test
    public void shouldOverwriteSerializerStrategy() throws JsonParseException, JsonMappingException, IOException {
        UserRequestTest request = new UserRequestTest("user name", "user address");
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Origin", "*");
        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        
        JsonResponseBodySerializerStrategy serializer = new JsonResponseBodySerializerStrategy();
        
        ResponseEntity response = ResponseEntity.of(request, 
                headers, 
                HttpStatus.SC_CONTINUE,                 
                serializer);
        
        Assert.assertEquals(HttpStatus.SC_CONTINUE, response.getStatusCode());      
        Assert.assertNotNull(response.getBody());
        
        Map<String, String> responseHeaders = response.getHeaders();
        
        Assert.assertNotNull(responseHeaders);
        Assert.assertEquals("*", responseHeaders.get("Access-Control-Allow-Origin"));
        Assert.assertEquals(ContentType.APPLICATION_JSON.getMimeType(), 
                responseHeaders.get(HttpHeaders.CONTENT_TYPE));
        
        //should be able to deserialize
        UserRequestTest deserialized = new ObjectMapper().readValue(response.getBody(), UserRequestTest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(request.getName(), deserialized.getName());
        Assert.assertEquals(request.getAddress(), deserialized.getAddress());
    }
        
}
