package br.com.tdsis.lambda.forest.auth.handler;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.tdsis.lambda.forest.auth.APIGatewayAuthorizer;
import br.com.tdsis.lambda.forest.auth.domain.AuthPolicy;
import br.com.tdsis.lambda.forest.auth.domain.AuthPolicyBuilder;
import br.com.tdsis.lambda.forest.auth.domain.AuthRequest;
import br.com.tdsis.lambda.forest.auth.domain.PolicyStatement;
import br.com.tdsis.lambda.forest.http.exception.HttpException;

/**
 * AbstractAPIGatewayAuthorizer is the abstract base class
 * that handles a custom authorization request from API Gateway.
 * 
 * <p>
 * Every class that inherits this class must return an
 * {@code AuthPolicy} (the representation of an IAM Policy)
 * allowing or denying access to API Gateway resources.
 * 
 * <p>
 * The initial request payload that this handler receives
 * from API Gateway comes in the following format:
 * <br>
 * <pre>
 * <code>
 *  {
 *      "type": "TOKEN",
 *      "authorizationToken": "caller-supplied-token"
 *      "methodArn": "arn:aws:execute-api:regionId:accountId:apiId/stage/method/resourcePath"
 *  }
 * </code>
 * </pre> 
 * 
 * <p>
 * <b>Note:</b> 
 * <br> Any {@code HttpException} will result in an IAM Policy that denies access to the requested resource.
 * <br> Any other exception not handled will result in an IAM Policy that denies access to all API Gateway resources.
 * 
 * @author nmelo
 * @see <a href="http://docs.aws.amazon.com/apigateway/latest/developerguide/use-custom-authorizer.html">
 *          AWS Custom Authorizer</a>
 */
public abstract class AbstractAPIGatewayAuthorizer 
    implements RequestHandler<AuthRequest, Map<String, String>>,
               APIGatewayAuthorizer {
    
    private ObjectMapper mapper = new ObjectMapper();
        
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> handleRequest(AuthRequest request, Context context) {
        AuthPolicy policy = null;
        
        try {
            
            String methodArn = request.getMethodArn();
            String [] arnPartials = methodArn.split(":");
            
            String region = arnPartials[3];
            String awsAccountId = arnPartials[4];
            
            String [] apiGatewayArnPartials = arnPartials[5].split("/");
            String restApiId = apiGatewayArnPartials[0];
            String stage = apiGatewayArnPartials[1];
            String httpMethod = apiGatewayArnPartials[2];
            
            String resource = "";			
            for (int i = 3; i < apiGatewayArnPartials.length; i++) {
                resource = resource.concat("/");
                resource = resource.concat(apiGatewayArnPartials[i]);
            }
            
            request.setRegion(region);
            request.setAwsAccountId(awsAccountId);
            request.setApiId(restApiId);
            request.setStage(stage);
            request.setHttpMethod(httpMethod);
            request.setResource(resource);
            
            policy = authorize(request, context);
        
        } catch (HttpException e) {	
            PolicyStatement statement = new PolicyStatement(
                    AuthPolicy.ACTION_INVOKE, 
                    AuthPolicy.DENY, 
                    request.getMethodArn());
                        
            policy = new AuthPolicyBuilder("*")					
                    .addPolicyStatement(statement)
                    .build();
            
        } catch (Exception e) {			
            policy = new AuthPolicyBuilder("*")
                        .denyAll()
                        .build();			
        }
        
        Map<String, String> authPolicy = mapper.convertValue(policy, Map.class);
        return authPolicy; 
    }
    
}
