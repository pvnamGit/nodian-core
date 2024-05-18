package com.nodian.infrastructure.repository.user;

import com.nodian.entity.user.User;

public interface UserRepository {
    void persist(User user);
}
