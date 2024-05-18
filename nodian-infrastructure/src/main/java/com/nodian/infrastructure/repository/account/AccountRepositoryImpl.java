package com.nodian.infrastructure.repository.account;

import com.nodian.entity.account.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @PersistenceContext
    EntityManager entityManager;

    private Account getSingleResult(String param, String value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> root = cq.from(Account.class);
        Predicate predicate = cb.equal(root.get(param), value);
        cq.where(predicate);
        try {
            return entityManager.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null; // Or throw a custom exception if needed
        }
    }

    @Override
    public Account findByEmail(String email) {
        return getSingleResult("email", email);
    }

    @Override
    @Transactional
    public void persist(Account account) {
        entityManager.persist(account);
        entityManager.flush();
    }
}
