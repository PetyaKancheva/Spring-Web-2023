package org.softuni.mobilele.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {
//   private String first;
//    private String second;

//    String message() default "Fields are not matching";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
}
