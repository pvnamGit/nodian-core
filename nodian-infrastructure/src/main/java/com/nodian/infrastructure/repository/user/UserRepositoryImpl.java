package com.nodian.infrastructure.repository.user;

import com.nodian.entity.user.User;
import com.nodian.entity.user.UserRepository;
import com.nodian.infrastructure.repository.shared.BaseRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {
    @PersistenceContext
    EntityManager entityManager;

    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class);
    }
}
