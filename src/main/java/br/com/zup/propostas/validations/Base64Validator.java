package br.com.zup.propostas.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import static java.util.Base64.getDecoder;

public class Base64Validator implements ConstraintValidator<Base64,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            getDecoder().decode(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
