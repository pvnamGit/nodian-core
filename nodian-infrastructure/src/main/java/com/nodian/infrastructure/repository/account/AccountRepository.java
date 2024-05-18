package com.nodian.infrastructure.repository.account;

import com.nodian.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AccountRepository  {
    Account findByEmail(String email);
    void persist(Account account);
}
