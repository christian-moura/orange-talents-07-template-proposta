package br.com.zup.propostas.validations;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private EntityManager manager;
    private Class<?> entity;
    private String attribute;

    public UniqueValueValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
       this.entity = constraintAnnotation.entity();
       this.attribute = constraintAnnotation.attribute();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select e from "+entity.getName()+ " e where e."+attribute+" = :value");
        query.setParameter("value", value);
        return query.getResultList().isEmpty();
    }
}
