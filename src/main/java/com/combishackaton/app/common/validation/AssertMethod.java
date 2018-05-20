package com.combishackaton.app.common.validation;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {AssertMethodValidator.class} )
@Documented
public @interface AssertMethod {

    String message() default "{value} returned false";

    String value() default "isValid";

    Class[] groups() default {};

    Class[] payload() default {};
}
