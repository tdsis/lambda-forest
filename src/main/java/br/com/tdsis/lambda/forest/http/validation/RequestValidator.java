package br.com.tdsis.lambda.forest.http.validation;

import br.com.tdsis.lambda.forest.http.exception.HttpException;

/**
 * The RequestValidator interface.
 * <p>
 * It defines a contract to be implemented by a request body validator.
 *
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 * @see {@link DefaultRequestValidator}
 */
public interface RequestValidator {

    /**
     * Validates a request bean
     * 
     * @param entity The request bean
     * @throws HttpException The HttpException.
     *                       A HttpException should be throw if any constraint is violated
     */
    void validate(Object entity) throws HttpException;
    
}
