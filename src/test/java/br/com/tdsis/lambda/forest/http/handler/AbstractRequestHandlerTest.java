package br.com.tdsis.lambda.forest.http.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdsis.lambda.forest.domain.ConstraintViolationDescription;
import br.com.tdsis.lambda.forest.domain.ConstraintViolationResponseError;
import br.com.tdsis.lambda.forest.http.HttpMethod;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.request.HttpRequest;
import br.com.tdsis.lambda.forest.http.response.ResponseEntity;
import br.com.tdsis.lambda.forest.http.validation.DefaultRequestValidator;

public class AbstractRequestHandlerTest {
        
    @Mock
    private Context context;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test   
    public void shouldCallBeforeMethod() throws JsonProcessingException, HttpException {                    
        LambdaHandlerTest handler = Mockito.spy(new LambdaHandlerTest());
                        
        HttpRequest request = buildHttpRequest(new UserRequestTest("username", "user address"));
        handler.handleRequest(request, context);
                
        Mockito.verify(handler, Mockito.times(1)).before(context);
    }
    
    @Test
    public void shouldCallExecuteMethod() throws JsonProcessingException, HttpException {               
        LambdaHandlerTest handler = Mockito.spy(new LambdaHandlerTest());
                        
        HttpRequest request = buildHttpRequest(new UserRequestTest("username", "user address"));
        handler.handleRequest(request, context);
                        
        Mockito.verify(handler, Mockito.times(1)).execute(Matchers.any(UserRequestTest.class), Matchers.eq(context));
    }
    
    @Test
    public void shouldCallBeforeAndExecuteMethods() throws JsonProcessingException, HttpException {             
        LambdaHandlerTest handler = Mockito.spy(new LambdaHandlerTest());
                        
        HttpRequest request = buildHttpRequest(new UserRequestTest("username", "user address"));
        handler.handleRequest(request, context);
        
        Mockito.verify(handler, Mockito.times(1)).before(context);
        Mockito.verify(handler, Mockito.times(1)).execute(Matchers.any(UserRequestTest.class), Matchers.eq(context));
    }
    
    @Test
    public void shouldThrowUnprocessableEntityException() throws HttpException, IOException {               
        LambdaHandlerWithValidationTest handler = new LambdaHandlerWithValidationTest();
                        
        HttpRequest request = buildHttpRequest(new UserRequestTest("username", ""));
        ResponseEntity response = handler.handleRequest(request, context);
                    
        ConstraintViolationResponseError error = new ObjectMapper().readValue(
                response.getBody(), 
                ConstraintViolationResponseError.class);
        
        ConstraintViolationDescription description = error.getErrors().get(0);
        
        Assert.assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, response.getStatusCode());
        Assert.assertEquals(DefaultRequestValidator.UNPROCESSABLE_ENTITY_MESSAGE, error.getMessage());
        Assert.assertEquals("address", description.getAttribute());
        Assert.assertEquals(UserRequestTest.INVALID_ADDRESS_MESSAGE, description.getMessage());     
    }
    
    
    @Test
    public void shouldNotSerializeResponse() throws HttpException, IOException {                
        LambdaHandlerWithNoSerializedResponseTest handler = 
                Mockito.spy(new LambdaHandlerWithNoSerializedResponseTest());
                        
        HttpRequest request = buildHttpRequest(new UserRequestTest("username", ""));
        ResponseEntity response = handler.handleRequest(request, context);
                                
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        
        Mockito.verify(handler, Mockito.times(1)).before(context);
        Mockito.verify(handler, Mockito.times(1)).execute(Matchers.any(UserRequestTest.class), Matchers.eq(context));       
    }
    
    @Test
    public void shouldNotDeserializeRequest() throws HttpException, IOException {               
        LambdaHandlerWithNoDeserializedRequestTest handler = 
                Mockito.spy(new LambdaHandlerWithNoDeserializedRequestTest());
                        
        HttpRequest request = buildHttpRequest(new UserRequestTest("username", "user address"));
        ResponseEntity response = handler.handleRequest(request, context);
                                
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        
        Mockito.verify(handler, Mockito.times(1)).before(context);
        Mockito.verify(handler, Mockito.times(1)).execute(Matchers.eq(request), Matchers.eq(context));      
    }
    
    @Test
    public void shouldSerializeWithDefaultSerializerStrategy() throws HttpException, IOException {              
        LambdaHandlerWithNoSerializedResponseTest handler = 
                Mockito.spy(new LambdaHandlerWithNoSerializedResponseTest());
                        
        String json = new ObjectMapper().writeValueAsString(new UserRequestTest("username", "user address"));
        
        Map<String, String> headers = new HashMap<>();      
        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        headers.put(HttpHeaders.ACCEPT, ContentType.APPLICATION_XML.getMimeType());
        
        HttpRequest request = new HttpRequest();
        request.setHeaders(headers);
        request.setHttpMethod("POST");
        request.setResource("users");
        request.setPath("/users");
        request.setBody(json);
                    
        ResponseEntity response = handler.handleRequest(request, context);                          
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        
        Mockito.verify(handler, Mockito.times(1)).before(context);
        Mockito.verify(handler, Mockito.times(1)).execute(Matchers.any(UserRequestTest.class), Matchers.eq(context));       
    }
    
    @Test
    public void shouldThrowInternalServerErrorException() throws HttpException, IOException {               
        LambdaHandlerWithInternalServerErrorTest handler = 
                Mockito.spy(new LambdaHandlerWithInternalServerErrorTest());
                        
        HttpRequest request = buildHttpRequest(new UserRequestTest("username", ""));
        ResponseEntity response = handler.handleRequest(request, context);
                                
        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatusCode());
        
        Mockito.verify(handler, Mockito.times(1)).before(context);
        Mockito.verify(handler, Mockito.times(1)).execute(Matchers.any(UserRequestTest.class), Matchers.eq(context));       
    }
    
    
    @Test
    public void shouldBeOk() throws JsonProcessingException, HttpException {                
        LambdaHandlerTest handler = Mockito.spy(new LambdaHandlerTest());
                        
        HttpRequest request = buildHttpRequest(new UserRequestTest("username", "user address"));        
        ResponseEntity response = handler.handleRequest(request, context);
        
        Mockito.verify(handler, Mockito.times(1)).before(context);
        Mockito.verify(handler, Mockito.times(1)).execute(Matchers.any(UserRequestTest.class), Matchers.eq(context));
                
        String httpMethod = handler.getHttpMethod();
        Optional<String> contentType = handler.getHeader(HttpHeaders.CONTENT_TYPE);
        Optional<String> accept = handler.getHeader(HttpHeaders.ACCEPT);
        String path = handler.getPath();
        String resource = handler.getResource();
        Map<String, String> headers = handler.getResponseHeaders();
        Optional<String> stageVars = handler.getStageVariable("none");
        Optional<String> additional = handler.getAdditionalProperty("none");
        Optional<String> pathParameter = handler.getPathParameter("none");
        Optional<String> queryStringParam = handler.getQueryStringParameter("none");
        String rawBody = handler.getRawRequestBody();
                
        Assert.assertEquals(HttpMethod.POST.name(), httpMethod);
        
        Assert.assertTrue(contentType.isPresent());
        Assert.assertTrue(contentType.get().equals(ContentType.APPLICATION_JSON.getMimeType()));
        
        Assert.assertTrue(accept.isPresent());
        Assert.assertTrue(accept.get().equals(ContentType.APPLICATION_JSON.getMimeType()));
        
        Assert.assertEquals("/users", path);
        Assert.assertEquals("users", resource);
        Assert.assertNotNull(headers);
        
        Assert.assertFalse(stageVars.isPresent());
        Assert.assertFalse(additional.isPresent());
        Assert.assertFalse(pathParameter.isPresent());
        Assert.assertFalse(queryStringParam.isPresent());
        
        Assert.assertNotNull(rawBody);
        
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
    }
    
    private HttpRequest buildHttpRequest(UserRequestTest userRequest) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(userRequest);
        
        Map<String, String> headers = new HashMap<>();      
        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        headers.put(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        
        HttpRequest request = new HttpRequest();
        request.setHeaders(headers);
        request.setHttpMethod("POST");
        request.setResource("users");
        request.setPath("/users");
        request.setBody(json);
        
        return request;
    }


}
