package br.com.tdsis.lambda.forest.auth.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The AuthPolicyBuilder class.
 
 * <p>
 * This is a helper class to build an AuthPolicy (IAM Policy representation)
 * 
 * @author nmelo 
 */
public class AuthPolicyBuilder {
	
	private final String ARN_FORMAT = "arn:aws:execute-api:%s:%s:%s/%s/%s/%s";
	
	private String principalId;
	private PolicyDocument policyDocument = new PolicyDocument();
	private List<PolicyStatement> policyStatements = new ArrayList<>();
	private Map<String, String> context = new HashMap<>();
	
	/**
	 * The default constructor
	 */
	public AuthPolicyBuilder() {
		this(null);
	}
	
	/**
	 * The AuthPolicyBuilder constructor
	 * 
	 * @param principalId The principal ID
	 */
	public AuthPolicyBuilder(String principalId) {
		this.principalId = principalId;
		policyDocument.setStatements(policyStatements);
	}
			
	/**
	 * Sets the principal Identifier
	 * 
	 * @param principalId The principal ID
	 * @return builder The AuthPolicyBuilder instance
	 */
	public AuthPolicyBuilder withPrincipalId(String principalId) {
		this.principalId = principalId;
		return this;
	}
	
	/**
	 * Sets the context map
	 * 
	 * @param context The context map
	 * @return builder The AuthPolicyBuilder instance 
	 */
	public AuthPolicyBuilder withContext(Map<String, String> context) {
		this.context = context;
		return this;
	}
		
	/**
	 * Adds a policy statement to this policy document
	 * 
	 * @param statement The policy statement
	 * @return builder The AuthPolicyBuilder instance
	 */
	public AuthPolicyBuilder addPolicyStatement(PolicyStatement statement) {
		this.policyStatements.add(statement);
		return this;
	}
	
	/**
	 * Adds a key value to this context
	 * 
	 * @param key 	The key
	 * @param value The value
	 * @return builder The AuthPolicyBuilder instance
	 */
	public AuthPolicyBuilder addToContext(String key, String value) {
		this.context.put(key, value);
		return this;
	}
	
	/**
	 * Creates an IAM Policy that denies access to all API Gateway resources.
	 * 
	 * @return builder The AuthPolicyBuilder instance
	 */
	public AuthPolicyBuilder denyAll() {
		policyStatements.clear();
		
		String deniedArn = arn("*", "*", "*", "*", "*", "*"); 
		PolicyStatement denied = policyStatement(PolicyAction.INVOKE, PolicyEffect.DENY, deniedArn);
		policyStatements.add(denied);
		
		return this;
	}
	
	/**
	 * Creates an AuthPolicy instance
	 * 
	 * @return authPolicy The auth policy instance
	 */
	public AuthPolicy build() {				
		return new AuthPolicy(principalId, policyDocument, context);
	}
	
	/**
	 * Creates a policy statement
	 * 
	 * @param action   The action
	 * @param effect   The effect
	 * @param resource The resource
	 * @return statement The policy statement
	 */
	private PolicyStatement policyStatement(PolicyAction action, PolicyEffect effect, String resource) {
		return new PolicyStatement(action, effect, resource);
	}
	
	/**
	 * Formats an ARN
	 * 
	 * @param region  	   The region
	 * @param awsAccountId The aws account id
	 * @param apiId		   The application id
	 * @param resource	   The resource
	 * @param method       The method
	 * @return arn		   The formated ARN
	 */
	private String arn(String region, String awsAccountId, String apiId, String stage, String method, String resource) {
		return String.format(ARN_FORMAT, region, awsAccountId, apiId, stage, resource, method);
	}
}
