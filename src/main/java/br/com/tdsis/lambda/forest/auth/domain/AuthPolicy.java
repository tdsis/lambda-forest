package br.com.tdsis.lambda.forest.auth.domain;

import java.util.Map;

/**
 * The AuthPolicy class.
 * 
 * <p>
 * This class is a representation of an IAM Policy
 * 
 * @author nmelo 
 */
public class AuthPolicy {
	
	private String principalId;
	private PolicyDocument policyDocument;
	private Map<String, String> context;

	/**
	 * The default constructor
	 */
	public AuthPolicy() {
		
	}
	
	/**
	 * The class constructor
	 * 
	 * @param principalId    The principal identifier
	 * @param policyDocument The policy document
	 * @param context        The context
	 */
	public AuthPolicy(String principalId, PolicyDocument policyDocument, Map<String, String> context) {
		this.principalId = principalId;
		this.policyDocument = policyDocument;
		this.context = context;
	}
	
	/**
	 * Returns the principal id
	 * 
	 * @return principalId The principal id
	 */
	public String getPrincipalId() {
		return principalId;
	}

	/**
	 * Sets the principal id
	 * 
	 * @param principalId The principal id
	 */
	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}

	/**
	 * Returns the policy document
	 * 
	 * @return policyDocument The policy document
	 */
	public PolicyDocument getPolicyDocument() {
		return policyDocument;
	}

	/**
	 * Sets the policy document
	 * 
	 * @param policyDocument The policy document
	 */
	public void setPolicyDocument(PolicyDocument policyDocument) {
		this.policyDocument = policyDocument;
	}
	
	/**
	 * Returns the context map
	 * 
	 * @return context The context map
	 */
	public Map<String, String> getContext() {
		return context;
	}

	/**
	 * Sets the context map
	 * 
	 * @param context The context map
	 */
	public void setContext(Map<String, String> context) {
		this.context = context;
	}

}
