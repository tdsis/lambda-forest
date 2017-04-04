package br.com.tdsis.lambda.forest.domain;

/**
 * The ConstraintViolationDescription class
 * <p>
 * This class represents a constraint violation when 
 * a request body object is validated.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConstraintViolationDescription {

    private String message;
    private String attribute;

    /**
     * The default constructor
     */
    public ConstraintViolationDescription() {
        
    }

    /**
     * The constructor with message and attribute args to be set
     * 
     * @param message The constraint violation message
     * @param attribute The attribute name that failed on validate
     */
    public ConstraintViolationDescription(String message, String attribute) {
        this.message = message;
        this.attribute = attribute;
    }

    /**
     * Returns the constraint violation message
     * 
     * @return message The constraint violation message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the constraint violation message
     * 
     * @param message The constraint violation message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the attribute name 
     * 
     * @return attribute The attribute name 
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Sets the attribute name
     * 
     * @param attribute The attribute name
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
