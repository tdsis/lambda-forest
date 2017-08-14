package br.com.tdsis.lambda.forest.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PolicyAction enum
 * <p>
 * 
 * @author nmelo
 *
 */
public enum PolicyAction {
	
	@JsonProperty("execute-api:Invoke")
	INVOKE
	
}
