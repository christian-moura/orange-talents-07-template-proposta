package br.com.zup.propostas.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValueValidator.class)
@Documented
public @interface UniqueValue {
    String message() default "Valor do atributo indisponível para cadastro. Informe outro.";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
    Class<?> entity();
    String attribute() ;
}
