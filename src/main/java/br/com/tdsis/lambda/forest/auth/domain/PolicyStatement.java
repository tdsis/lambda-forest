package br.com.tdsis.lambda.forest.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PolicyStatement class.
 * 
 * <p>
 * This is a representation of an IAM Policy Statement.
 * 
 * @author nmelo 
 */
public class PolicyStatement {
	
	@JsonProperty("Action")
	private PolicyAction action;
	
	@JsonProperty("Effect")
	private PolicyEffect effect;
	
	@JsonProperty("Resource")
	private String resource;

	/**
	 * The default constructor
	 */
	public PolicyStatement() {
		
	}
	
	/**
	 * The class constructor 
	 * 
	 * @param action   The action
	 * @param effect   The effect
	 * @param resource The resource
	 */
	public PolicyStatement(PolicyAction action, PolicyEffect effect, String resource) {
		this.action = action;
		this.effect = effect;
		this.resource = resource;
	}
	
	/**
	 * Returns the action
	 * 
	 * @return action The action
	 */
	public PolicyAction getAction() {
		return action;
	}

	/**
	 * Sets the action
	 * 
	 * @param action The action
	 */
	public void setAction(PolicyAction action) {
		this.action = action;
	}
	
	/**
	 * Returns the effect
	 * 
	 * @return effect The effect
	 */
	public PolicyEffect getEffect() {
		return effect;
	}
	
	/**
	 * Sets the effect
	 * 
	 * @param effect The effect
	 */
	public void setEffect(PolicyEffect effect) {
		this.effect = effect;
	}
	
	/**
	 * Returns the resource
	 * 
	 * @return resource The resource
	 */
	public String getResource() {
		return resource;
	}
	
	/**
	 * Sets the resource
	 * 
	 * @param resource The resource
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

}
