package br.com.zup.propostas.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class ExistEnumValidator implements ConstraintValidator<ExistEnum,String> {

    private List<String> enumList;
    private  Class<? extends Enum> enumSelected;

    @Override
    public void initialize(ExistEnum targetEnum) {
        this.enumSelected = targetEnum.targetClassType();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        enumList =  (List<String>) EnumSet.allOf(enumSelected).stream().map(e -> ((Enum<? extends Enum<?>>) e).name()).collect(Collectors.toList());
        return enumList.contains(value);
    }

}
