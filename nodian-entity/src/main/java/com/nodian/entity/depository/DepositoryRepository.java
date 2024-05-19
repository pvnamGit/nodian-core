package com.nodian.entity.depository;

import com.nodian.entity.shared.BaseRepository;

import java.util.List;

public interface DepositoryRepository extends BaseRepository<Depository, Long> {
    List<Depository> findByOwner(Long userId);

    void updateName(Long id, String name);
}
