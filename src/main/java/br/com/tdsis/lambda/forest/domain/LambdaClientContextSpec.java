package br.com.tdsis.lambda.forest.domain;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Client;
import com.amazonaws.services.lambda.runtime.ClientContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * The LambdaClientContextSpec class
 * <p>
 * This class represents a {@code ClientContext} 
 * during the lambda handler execution. 
 * <p> 
 * It should be used only for local tests functionality
 * using {@code LambdaRunner}
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link LambdaRunner}
 */
public class LambdaClientContextSpec implements ClientContext {

    @JsonDeserialize(as = LambdaClientSpec.class)
    private Client client;
    private Map<String, String> custom;
    private Map<String, String> environment;

    /**
     * Returns the {@code Client}
     * 
     * @see {@link ClientContext#getClient()}}
     */
    public Client getClient() {
        return client;
    }

    /**
     * Returns a map of custom
     * 
     * @see {@link ClientContext#getCustom()}}
     */
    public Map<String, String> getCustom() {
        return custom;
    }

    /**
     * Returns a map of environment
     * 
     * @see {@link ClientContext#getEnvironment()}}
     */
    public Map<String, String> getEnvironment() {
        return environment;
    }
}
