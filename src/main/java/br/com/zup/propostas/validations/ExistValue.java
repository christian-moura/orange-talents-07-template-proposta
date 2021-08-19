package br.com.zup.propostas.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = ExistValueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExistValue {
    String message() default "Valor do atributo informado é inexistente";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
    Class<?> entity();
    String attribute();
}
