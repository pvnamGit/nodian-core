package com.nodian.domain.depository.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class DepositoryUpdateREQ {
    @NotEmpty()
    @Length(min = 1,max = 150)
    private String name;
}
