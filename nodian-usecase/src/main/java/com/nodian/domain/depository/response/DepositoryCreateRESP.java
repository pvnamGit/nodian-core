package com.nodian.domain.depository.response;

import com.nodian.entity.depository.Depository;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepositoryCreateRESP {
    private Long id;
    public static DepositoryCreateRESP build(Depository depository) {
        return DepositoryCreateRESP.builder()
                .id(depository.getId())
                .build();
    }
}
