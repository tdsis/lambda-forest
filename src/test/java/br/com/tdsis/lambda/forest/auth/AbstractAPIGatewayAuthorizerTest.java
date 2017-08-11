package br.com.tdsis.lambda.forest.auth;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdsis.lambda.forest.auth.domain.AuthPolicy;
import br.com.tdsis.lambda.forest.auth.domain.AuthRequest;
import br.com.tdsis.lambda.forest.auth.domain.PolicyAction;
import br.com.tdsis.lambda.forest.auth.domain.PolicyDocument;
import br.com.tdsis.lambda.forest.auth.domain.PolicyEffect;
import br.com.tdsis.lambda.forest.auth.domain.PolicyStatement;
import br.com.tdsis.lambda.forest.http.exception.HttpException;


public class AbstractAPIGatewayAuthorizerTest {

	@Mock
	private Context context;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCallAuthorizeMethod() throws HttpException {
		CustomAPIGatewayAuthorizerTest authorizer = Mockito.spy(new CustomAPIGatewayAuthorizerTest());
		AuthRequest request = buildAuthRequest();
		
		authorizer.handleRequest(request, context);
		Mockito.verify(authorizer, Mockito.times(1)).authorize(request, context);
	}
	
	@Test
	public void shouldAllowRequest() throws HttpException, JsonProcessingException {
		CustomAPIGatewayAuthorizerTest authorizer = Mockito.spy(new CustomAPIGatewayAuthorizerTest());
		AuthRequest request = buildAuthRequest();
		
		Map<String, String> policy = authorizer.handleRequest(request, context);
		Mockito.verify(authorizer, Mockito.times(1)).authorize(request, context);
							
		AuthPolicy authPolicy = new ObjectMapper().convertValue(policy, AuthPolicy.class);
		PolicyDocument policyDocument = authPolicy.getPolicyDocument();
		Assert.assertEquals("123456", authPolicy.getPrincipalId());
		Assert.assertEquals("2012-10-17", policyDocument.getVersion());
		Assert.assertEquals(1, authPolicy.getContext().size());
		
		List<PolicyStatement> policyStatements = policyDocument.getStatements();	
		Assert.assertEquals(1, policyStatements.size());
		
		PolicyStatement statement = policyStatements.get(0);
		Assert.assertEquals(PolicyAction.INVOKE, statement.getAction());
		Assert.assertEquals(PolicyEffect.ALLOW, statement.getEffect());
		Assert.assertEquals(request.getMethodArn(), statement.getResource());
	}
	
	@Test
	public void unauthorizedExceptionShouldDenyRequest() throws HttpException {
		AuthorizerWithHttpExceptionTest authorizer = Mockito.spy(new AuthorizerWithHttpExceptionTest());
		AuthRequest request = buildAuthRequest();
		
		Map<String, String> policy = authorizer.handleRequest(request, context);
		Mockito.verify(authorizer, Mockito.times(1)).authorize(request, context);
		
		AuthPolicy authPolicy = new ObjectMapper().convertValue(policy, AuthPolicy.class);
		PolicyDocument policyDocument = authPolicy.getPolicyDocument();
		Assert.assertEquals("*", authPolicy.getPrincipalId());
		Assert.assertEquals("2012-10-17", policyDocument.getVersion());
		Assert.assertEquals(0, authPolicy.getContext().size());
				
		List<PolicyStatement> policyStatements = policyDocument.getStatements();	
		Assert.assertEquals(1, policyStatements.size());
		
		PolicyStatement statement = policyStatements.get(0);
		Assert.assertEquals(PolicyAction.INVOKE, statement.getAction());
		Assert.assertEquals(PolicyEffect.DENY, statement.getEffect());
		Assert.assertEquals(request.getMethodArn(), statement.getResource());
	}
	
	@Test
	public void malformedArnShouldDenyAll() throws HttpException {
		CustomAPIGatewayAuthorizerTest authorizer = Mockito.spy(new CustomAPIGatewayAuthorizerTest());
		AuthRequest request = buildInvalidAuthRequest();
		
		Map<String, String> policy = authorizer.handleRequest(request, context);
		Mockito.verify(authorizer, Mockito.times(0)).authorize(request, context);
		
		AuthPolicy authPolicy = new ObjectMapper().convertValue(policy, AuthPolicy.class);
		PolicyDocument policyDocument = authPolicy.getPolicyDocument();
		Assert.assertEquals("*", authPolicy.getPrincipalId());
		Assert.assertEquals("2012-10-17", policyDocument.getVersion());
		Assert.assertEquals(0, authPolicy.getContext().size());
				
		List<PolicyStatement> policyStatements = policyDocument.getStatements();	
		Assert.assertEquals(1, policyStatements.size());
		
		PolicyStatement statement = policyStatements.get(0);
		Assert.assertEquals(PolicyAction.INVOKE, statement.getAction());
		Assert.assertEquals(PolicyEffect.DENY, statement.getEffect());
		Assert.assertEquals("arn:aws:execute-api:*:*:*/*/*/*", statement.getResource());
	}
	
	private AuthRequest buildInvalidAuthRequest() {
		AuthRequest request = new AuthRequest();
		request.setType("TOKEN");
		request.setAuthorizationToken("any");
		request.setMethodArn(":regionId:accountId:apiId/stage/method/resourcePath");
		
		return request;
	}
	
	private AuthRequest buildAuthRequest() {
		AuthRequest request = new AuthRequest();
		request.setType("TOKEN");
		request.setAuthorizationToken("any");
		request.setMethodArn("arn:aws:execute-api:regionId:accountId:apiId/stage/method/resourcePath");
		
		return request;
	}
}
