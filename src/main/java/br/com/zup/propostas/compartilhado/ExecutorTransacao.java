package br.com.zup.propostas.compartilhado;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.function.Supplier;

@Component
public class ExecutorTransacao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public <T> T salvar(T object){
        manager.persist(object);
        return object;
    }

    @Transactional
    public <T> T atualizarECommitar(T object){
        manager.merge(object);
        return object;
    }

    @Transactional
    public <T> T executor(Supplier<T> action){
        return action.get();
    }

    public EntityManager getManager() {
        return manager;
    }
}
