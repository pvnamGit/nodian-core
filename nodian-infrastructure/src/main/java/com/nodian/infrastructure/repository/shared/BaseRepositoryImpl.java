package com.nodian.infrastructure.repository.shared;

import com.nodian.entity.shared.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public abstract class BaseRepositoryImpl<T, ID extends Serializable> implements BaseRepository<T, ID> {
    protected Class<T> entityClass;
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return entityManager.getCriteriaBuilder();
    }

    protected CriteriaQuery<T> createCriteriaQuery() {
        return getCriteriaBuilder().createQuery(entityClass);
    }

    protected Root<T> getRoot(CriteriaQuery<T> cq) {
        return cq.from(entityClass);
    }


    @Override
    public T findById(Long id) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery cq = createCriteriaQuery();
        Root<T> root = getRoot(cq);
        Predicate predicate = cb.equal(root.get("id"), id);
        cq.where(predicate);
        try {
            return (T) entityManager.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null; // Or throw a custom exception if needed
        }
    }

    @Override
    @Transactional
    public void persist(T t) {
        entityManager.persist(t);
    }

    @Override
    @Transactional
    public void persistAndFlush(T t) {
        entityManager.persist(t);
        entityManager.flush();
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery cq = createCriteriaQuery();
        try {
            return entityManager.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList(); // Or throw a custom exception if needed
        }
    }

    @Override
     public T getSingleResult(String param, String value) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery cq = createCriteriaQuery();
        Root<T> root = getRoot(cq);
        Predicate predicate = cb.equal(root.get(param), value);
        cq.where(predicate);
        try {
            return (T) entityManager.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null; // Or throw a custom exception if needed
        }
    }

    @Override
    @Transactional
    public void softDelete(Long id) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaUpdate<T> delete = cb.createCriteriaUpdate(entityClass);
        Root<T> root = delete.from(entityClass);
        delete.where(cb.equal(root.get("id"), id)); // Assuming "id" is the ID field
        delete.set("isActive", false);
        delete.set("updatedAt", Instant.now().toEpochMilli());
        entityManager.createQuery(delete).executeUpdate();
    }

    @Override
    @Transactional
    public void hardDelete(Long id) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaDelete<T> delete = cb.createCriteriaDelete(entityClass);
        Root<T> root = delete.from(entityClass);
        delete.where(cb.equal(root.get("id"), id)); // Assuming "id" is the ID field
        entityManager.createQuery(delete).executeUpdate();
    }
}
