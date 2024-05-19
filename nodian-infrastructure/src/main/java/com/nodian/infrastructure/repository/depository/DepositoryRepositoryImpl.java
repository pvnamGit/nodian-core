package com.nodian.infrastructure.repository.depository;

import com.nodian.entity.depository.Depository;
import com.nodian.entity.depository.DepositoryRepository;
import com.nodian.entity.user.User;
import com.nodian.infrastructure.repository.shared.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Repository
public class DepositoryRepositoryImpl extends BaseRepositoryImpl<Depository, Long> implements DepositoryRepository {
    @PersistenceContext
    EntityManager entityManager;

    public DepositoryRepositoryImpl(EntityManager entityManager) {
        super(Depository.class);
    }

    @Override
    public List<Depository> findByOwner(Long userId) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery cq = createCriteriaQuery();
        Root<Depository> root = getRoot(cq);
        Join<Depository, User> owner = root.join("owner", JoinType.LEFT);
        Predicate predicate = cb.equal(owner.get("id"), userId);
        cq.where(predicate);
        cq.orderBy(cb.asc(root.get("name")));
        try {
            return entityManager.createQuery(cq).getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList(); // Or throw a custom exception if needed
        }
    }

    @Override
    @Transactional
    public void updateName(Long id, String name) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaUpdate<Depository> update = cb.createCriteriaUpdate(Depository.class);
        Root<Depository> root = update.from(Depository.class);
        Predicate predicate = cb.equal(root.get("id"), id);
        update.where(predicate);
        update.set("name", name);
        update.set("updatedAt", Instant.now().toEpochMilli());
        entityManager.createQuery(update).executeUpdate();
    }
}
