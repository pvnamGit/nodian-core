package com.nodian.domain.depository.response;


import com.nodian.entity.depository.Depository;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DepositoryListRESP {
    private Long id;
    private String name;

    public static List<DepositoryListRESP> fromEntities(List<Depository> depositories) {
        return depositories.stream().map(depository -> DepositoryListRESP.builder()
                .id(depository.getId())
                .name(depository.getName())
                .build()).toList();
    }
}
