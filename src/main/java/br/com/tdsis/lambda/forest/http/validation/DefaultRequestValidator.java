package br.com.tdsis.lambda.forest.http.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.tdsis.lambda.forest.domain.ConstraintViolationDescription;
import br.com.tdsis.lambda.forest.domain.ConstraintViolationResponseError;
import br.com.tdsis.lambda.forest.http.exception.HttpException;
import br.com.tdsis.lambda.forest.http.exception.UnprocessableEntityException;

/**
 * The default request validation class.
 * <p>
 * It uses the Hibernate validator to perform a bean validation.
 * <p>
 * A {@code UnprocessableEntityException} will be thrown if any constraint is violated.
 * 
 * @author nmelo
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultRequestValidator implements RequestValidator {

    public static final String UNPROCESSABLE_ENTITY_MESSAGE = "Unprocessable entity";

    @Override
    public void validate(Object entity) throws HttpException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Object>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            List<ConstraintViolationDescription> errors = new ArrayList<>();

            ConstraintViolationResponseError error = new ConstraintViolationResponseError();
            error.setMessage(UNPROCESSABLE_ENTITY_MESSAGE);

            for (ConstraintViolation<Object> violation : violations) {
                String attribute = violation.getPropertyPath().toString();
                String message = violation.getMessage();
                errors.add(new ConstraintViolationDescription(message, attribute));
            }

            error.setErrors(errors);
            throw new UnprocessableEntityException(error);
        }
        
    }

}
