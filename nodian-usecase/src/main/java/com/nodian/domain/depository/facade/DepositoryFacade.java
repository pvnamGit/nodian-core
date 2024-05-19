package com.nodian.domain.depository.facade;

import com.nodian.domain.depository.request.DepositoryCreateREQ;
import com.nodian.domain.depository.request.DepositoryUpdateREQ;
import com.nodian.domain.depository.response.DepositoryCreateRESP;
import com.nodian.domain.depository.response.DepositoryListRESP;

import java.util.List;

public interface DepositoryFacade {
    DepositoryCreateRESP create(DepositoryCreateREQ req);

    List<DepositoryListRESP> findAll();

    void update(Long id, DepositoryUpdateREQ req);
    void delete(Long id);
}
