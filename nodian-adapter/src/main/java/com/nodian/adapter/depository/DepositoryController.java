package com.nodian.adapter.depository;

import com.nodian.adapter.shared.BaseEntityResponse;
import com.nodian.adapter.shared.BasePaginationResponse;
import com.nodian.domain.depository.facade.DepositoryFacade;
import com.nodian.domain.depository.request.DepositoryCreateREQ;
import com.nodian.domain.depository.request.DepositoryUpdateREQ;
import com.nodian.domain.depository.response.DepositoryCreateRESP;
import com.nodian.domain.depository.response.DepositoryListRESP;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${url.prefix}/depositories")
@AllArgsConstructor
@PreAuthorize("hasRole(USER)")
public class DepositoryController {
    private final DepositoryFacade depositoryFacade;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BaseEntityResponse create(@RequestBody @Valid DepositoryCreateREQ req) {
        DepositoryCreateRESP response = depositoryFacade.create(req);
        return BaseEntityResponse.success(response);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public BasePaginationResponse findAll() {
        List<DepositoryListRESP> depositories = depositoryFacade.findAll();
        return BasePaginationResponse.success(depositories);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseEntityResponse update(@PathVariable("id") Long id, @RequestBody @Valid DepositoryUpdateREQ req) {
        depositoryFacade.update(id, req);
        return BaseEntityResponse.success();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseEntityResponse delete(@PathVariable("id") Long id) {
        depositoryFacade.delete(id);
        return BaseEntityResponse.success();
    }
}
