package com.nodian.infrastructure.repository.account;

import com.nodian.entity.account.Account;
import com.nodian.entity.account.AccountRepository;
import com.nodian.infrastructure.repository.shared.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl extends BaseRepositoryImpl<Account, Long> implements AccountRepository {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public AccountRepositoryImpl(EntityManager entityManager) {
        super(Account.class);
    }

//    private Account getSingleResult(String param, String value) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
//        Root<Account> root = cq.from(Account.class);
//        Predicate predicate = cb.equal(root.get(param), value);
//        cq.where(predicate);
//        try {
//            return (Account) entityManager.createQuery(cq).getSingleResult();
//        } catch (NoResultException e) {
//            return null; // Or throw a custom exception if needed
//        }
//    }

    @Override
    public Account findByEmail(String email) {
        return getSingleResult("email", email);
    }
}
