package br.com.zup.propostas.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistEnumValidator.class)
@Documented
public @interface ExistEnum {
    String message() default "Valor inválido ou inexistente.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends Enum<?>> targetClassType();
}
