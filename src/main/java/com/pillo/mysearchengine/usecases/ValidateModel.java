package com.pillo.mysearchengine.usecases;

import com.pillo.mysearchengine.exceptions.BusinessException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidateModel {

    private final Validator validator;

    public ValidateModel(final Validator validator) {
        this.validator = validator;
    }

    public void validate(final Object object) {
        final Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            throw new BusinessException();
        }
    }

}
