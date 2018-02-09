package com.jacksondouglas.ordering.controller.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, Long timestamp, String error, String path) {
        super(status, message, timestamp, error, path);
    }

    public List<FieldMessage> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
