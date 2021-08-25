package br.com.zup.propostas.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Base64Validator.class)
@Documented
public @interface Base64 {
    String message() default "Atributo não está em Base64.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
