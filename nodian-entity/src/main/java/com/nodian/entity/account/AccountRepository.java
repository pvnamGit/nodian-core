package com.nodian.entity.account;

import com.nodian.entity.shared.BaseRepository;

public interface AccountRepository extends BaseRepository<Account, Long> {
    Account findByEmail(String email);
}
