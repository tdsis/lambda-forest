package br.com.tdsis.lambda.forest.auth.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PolicyDocument class.
 * 
 * <p>
 * This is a representation of an IAM Policy Document
 * 
 * @author nmelo
 */
public class PolicyDocument {

    /**
     * The default IAM Policy version
     */
    public static final String DEFAULT_VERSION = "2012-10-17";
    
    @JsonProperty("Version")
    private String version;

    @JsonProperty("Statement")
    private List<PolicyStatement> statements;
    
    /**
     * The default constructor
     */
    public PolicyDocument() {
        this.version = DEFAULT_VERSION;
    }
    
    /**
     * Returns the version
     * 
     * @return version The version
     */
    public String getVersion() {
        return version;
    }
    
    /**
     * Sets the version
     * 
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    /**
     * Returns the policy statements
     * 
     * @return statements The policy statements
     */
    public List<PolicyStatement> getStatements() {
        return statements;
    }
    
    /**
     * Sets the policy statements
     * 
     * @param statements The policy statements
     */
    public void setStatements(List<PolicyStatement> statements) {
        this.statements = statements;
    }

}
