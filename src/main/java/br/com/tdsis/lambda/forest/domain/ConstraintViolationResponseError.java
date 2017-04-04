package br.com.tdsis.lambda.forest.domain;

import java.util.List;

/**
 * The ConstraintViolationResponseError class
 * <p>
 * This class represents a response body when 
 * a request object fails during validation.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConstraintViolationResponseError {

    private String message;
    private List<ConstraintViolationDescription> errors;

    /**
     * Returns the reason message
     * 
     * @return message The reason message 
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the reason message
     * 
     * @param message the reason message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the list of errors
     * 
     * @return errors The list of errors
     */
    public List<ConstraintViolationDescription> getErrors() {
        return errors;
    }

    /**
     * Sets the list of errors
     * 
     * @param errors The list of errors
     */
    public void setErrors(List<ConstraintViolationDescription> errors) {
        this.errors = errors;
    }

}
