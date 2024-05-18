package com.nodian.infrastructure.repository.user;

import com.nodian.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    @Transactional
    public void persist(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }
}
