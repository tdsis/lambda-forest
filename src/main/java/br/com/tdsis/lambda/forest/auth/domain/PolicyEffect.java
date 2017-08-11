package br.com.tdsis.lambda.forest.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PolicyEffect enum
 * 
 * @author nmelo
 *
 */
public enum PolicyEffect {

	@JsonProperty("Allow")
	ALLOW,
	
	@JsonProperty("Deny")
	DENY;
	
}
