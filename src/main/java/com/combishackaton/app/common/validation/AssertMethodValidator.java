package com.combishackaton.app.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class AssertMethodValidator implements ConstraintValidator<AssertMethod, Object> {

    private String methodName;

    @Override
    public void initialize(AssertMethod assertMethod) {
        methodName = assertMethod.value();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Class clazz = object.getClass();
            Method validate = clazz.getMethod(methodName);
            return (Boolean) validate.invoke(object);
        } catch (Exception e) {
            return false;
        }
    }
}
