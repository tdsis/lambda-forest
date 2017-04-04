package br.com.tdsis.lambda.forest.domain;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.tdsis.lambda.forest.util.LambdaRunner;

/**
 * The LambdaSpec class
 * <p>
 * This class represents a spec to be 
 * created to simulate a lambda execution.
 * <p> 
 * It should be used only for local tests functionality
 * using {@code LambdaRunner}
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link LambdaRunner}
 */
public class LambdaSpec {

    @JsonDeserialize(as = LambdaContextSpec.class)
    private Context context;

    private LambdaRequestSpec request;

    /**
     * Returns the context
     * 
     * @return context The context
     * @see {@link Context}
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets the context
     * 
     * @param context The context
     * @see {@link Context}
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Returns the request
     * 
     * @return request The request to be performed
     */
    public LambdaRequestSpec getRequest() {
        return request;
    }

    /**
     * Sets the request
     * 
     * @param request The request to be performed
     */
    public void setRequest(LambdaRequestSpec request) {
        this.request = request;
    }

}
