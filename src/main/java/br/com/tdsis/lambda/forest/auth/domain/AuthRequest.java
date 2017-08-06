package br.com.tdsis.lambda.forest.auth.domain;

/**
 * The AuthRequest class.
 * 
 * <p>
 * This class is an representation of a request to be authorized.
 * 
 * @author nmelo
 */
public class AuthRequest {

    private String type;
    private String authorizationToken;
    private String methodArn;
    private String region;
    private String awsAccountId;
    private String apiId;
    private String stage;
    private String httpMethod;
    private String resource;

    /**
     * Returns the authorization type
     * 
     * @return type The authorization type
     */
    public String getType() {
        return type;
    }
    
    /**
     * Sets the authorization type
     * 
     * @param type The authorization type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the authorization token
     * 
     * @return authorizationToken The authorization token
     */
    public String getAuthorizationToken() {
        return authorizationToken;
    }

    /**
     * Sets the authorization token
     * 
     * @param authorizationToken The authorization token
     */
    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    /**
     * Returns the method ARN
     * 
     * @return methodArn The method ARN
     */
    public String getMethodArn() {
        return methodArn;
    }

    /**
     * Sets the method ARN
     * 
     * @param methodArn the method ARN
     */
    public void setMethodArn(String methodArn) {
        this.methodArn = methodArn;
    }

    /**
     * Returns the region
     * 
     * @return region The region
     */
    public String getRegion() {
        return region;
    }
    
    /**
     * Sets the region
     * 
     * @param region The region
     */
    public void setRegion(String region) {
        this.region = region;
    }
    
    /**
     * Returns the AWS Account Id
     * 
     * @return awsAccountId The AWS Account Id
     */
    public String getAwsAccountId() {
        return awsAccountId;
    }
    
    /**
     * Sets the AWS Account Id
     * 
     * @param awsAccountId The AWS Account Id
     */
    public void setAwsAccountId(String awsAccountId) {
        this.awsAccountId = awsAccountId;
    }
    
    /**
     * Returns the API Id
     * 
     * @return apiId The API Id
     */
    public String getApiId() {
        return apiId;
    }

    /**
     * Sets the API Id
     * 
     * @param apiId The API Id
     */
    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    /**
     * Returns the stage
     * 
     * @return stage The stage
     */
    public String getStage() {
        return stage;
    }
    
    /**
     * Sets the stage
     * 
     * @param stage The stage
     */
    public void setStage(String stage) {
        this.stage = stage;
    }
    
    /**
     * Returns the http method
     * 
     * @return httpMethod The http method
     */
    public String getHttpMethod() {
        return httpMethod;
    }
    
    /**
     * Sets the http method
     * 
     * @param httpMethod The http method
     */
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
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
