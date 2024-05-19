package com.nodian.domain.depository.service;

import com.nodian.domain.depository.facade.DepositoryFacade;
import com.nodian.domain.depository.request.DepositoryCreateREQ;
import com.nodian.domain.depository.request.DepositoryUpdateREQ;
import com.nodian.domain.depository.response.DepositoryCreateRESP;
import com.nodian.domain.depository.response.DepositoryListRESP;
import com.nodian.domain.security.SecurityCurrentUser;
import com.nodian.entity.account.ERole;
import com.nodian.entity.depository.Depository;
import com.nodian.entity.depository.DepositoryRepository;
import com.nodian.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@AllArgsConstructor
public class DepositoryFacadeImpl implements DepositoryFacade {
    private final DepositoryRepository depositoryRepository;
    private final SecurityCurrentUser currentUser;
    @PersistenceContext
    EntityManager entityManager;

    @SneakyThrows
    private Depository preAuthorizedAndFetch(Long id) throws AccessDeniedException {
        Depository depository = depositoryRepository.findById(id);
        if (depository == null) throw new EntityNotFoundException("Depository not found");
        if (currentUser.getCurrentUser().getAuthority() == ERole.ADMIN) return depository;
        if (depository.getOwner().getId().equals(currentUser.getCurrentUser().getUserId())) return depository;
        throw new AccessDeniedException("Current user unauthorized to access depository");
    }

    @Override
    public DepositoryCreateRESP create(DepositoryCreateREQ req) {
        User user = entityManager.getReference(User.class, currentUser.getCurrentUser().getUserId());
        Depository depository = Depository.builder()
                .name(req.getName())
                .owner(user)
                .build();
        depositoryRepository.persistAndFlush(depository);
        return DepositoryCreateRESP.build(depository);
    }

    @Override
    public List<DepositoryListRESP> findAll() {
        List<Depository> depositories = depositoryRepository
                .findByOwner(currentUser.getCurrentUser().getUserId());
        return DepositoryListRESP.fromEntities(depositories);
    }


    @SneakyThrows
    @Override
    public void update(Long id, DepositoryUpdateREQ req) {
        preAuthorizedAndFetch(id);
        depositoryRepository.updateName(id, req.getName());
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        preAuthorizedAndFetch(id);
        depositoryRepository.softDelete(id);
    }
}
