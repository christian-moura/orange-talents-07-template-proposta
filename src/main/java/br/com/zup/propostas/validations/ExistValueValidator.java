package br.com.zup.propostas.validations;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistValueValidator implements ConstraintValidator<ExistValue, Object> {
    private EntityManager manager;
    private Class<?> entity;
    private String attribute;

    public ExistValueValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(ExistValue constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
        this.attribute = constraintAnnotation.attribute();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select e from "+entity.getName()+ " e where e."+attribute+" = :value");
        query.setParameter("value", value);
        return !query.getResultList().isEmpty();
    }
}
