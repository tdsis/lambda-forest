package br.com.tdsis.lambda.forest.domain;

import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.tdsis.lambda.forest.util.LambdaRunner;

/**
 * The LambdaContextSpec class
 * <p>
 * This class represents a {@code Context} 
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
public class LambdaContextSpec implements Context {

    @JsonProperty("awsRequestId")
    private String awsRequestId;

    @JsonProperty("logGroupName")
    private String logGroupName;

    @JsonProperty("logStreamName")
    private String logStreamName;

    @JsonProperty("functionName")
    private String functionName;

    @JsonProperty("functionVersion")
    private String functionVersion;

    @JsonProperty("invokedFunctionArn")
    private String invokedFunctionArn;

    @JsonIgnore
    private CognitoIdentity identity;

    @JsonProperty("clientContext")
    @JsonDeserialize(as = LambdaClientContextSpec.class)
    private ClientContext clientContext;

    @JsonProperty("remainingTimeInMillis")
    private int remainingTimeInMillis;

    @JsonProperty("memoryLimitInMB")
    private int memoryLimitInMB;

    @JsonIgnore
    private LambdaLogger logger;

    /**
     * Returns the aws request id
     * 
     * @see {@link Context#getAwsRequestId()}
     */
    public String getAwsRequestId() {
        return awsRequestId;
    }
    
    /**
     * Returns the log group name
     * 
     * @see {@link Context#getLogGroupName()}
     */
    public String getLogGroupName() {
        return logGroupName;
    }

    /**
     * Returns the log stream name
     * 
     * @see {@link Context#getLogStreamName()}
     */
    public String getLogStreamName() {
        return logStreamName;
    }

    /**
     * Returns the function name
     * 
     * @see {@link Context#getFunctionName()}
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Returns the function version
     * 
     * @see {@link Context#getFunctionVersion()}
     */
    public String getFunctionVersion() {
        return functionVersion;
    }

    /**
     * Returns the invoked function arn
     * 
     * @see {@link Context#getInvokedFunctionArn()}
     */
    public String getInvokedFunctionArn() {
        return invokedFunctionArn;
    }
    
    /**
     * Returns the identity
     * 
     * @see {@link Context#getIdentity()}
     */
    public CognitoIdentity getIdentity() {
        throw new RuntimeException("Method not implemented yet");
    }

    /**
     * Returns the client context
     * 
     * @see {@link Context#getClientContext()}
     */
    public ClientContext getClientContext() {
        return clientContext;
    }

    /**
     * Returns the remaining time in millis
     * 
     * @see {@link Context#getRemainingTimeInMillis()}
     */
    public int getRemainingTimeInMillis() {
        return remainingTimeInMillis;
    }

    /**
     * Returns the memory limit in mb
     * 
     * @see {@link Context#getMemoryLimitInMB()}
     */
    public int getMemoryLimitInMB() {
        return memoryLimitInMB;
    }

    /**
     * Returns the lambda logger
     * 
     * @see {@link Context#getLogger()}
     */
    public LambdaLogger getLogger() {
        throw new RuntimeException("Method not implemented yet");
    }

}
