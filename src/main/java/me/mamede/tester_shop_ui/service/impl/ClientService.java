package me.mamede.tester_shop_ui.service.impl;

import lombok.AllArgsConstructor;
import me.mamede.tester_shop_ui.IClientRepository;
import me.mamede.tester_shop_ui.entity.ClientEntity;
import me.mamede.tester_shop_ui.service.IClientService;
import me.mamede.tester_shop_ui.service.query.IClientQueryService;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ClientService implements IClientService {

    private IClientRepository repository;
    private IClientQueryService queryService;


    @Override
    public ClientEntity save(ClientEntity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());

        return repository.save(entity);
    }

    @Override
    public ClientEntity update(ClientEntity entity) {
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());

        var stored = queryService.findById(entity.getId());
        stored.setName(entity.getName());
        stored.setPhone(entity.getPhone());
        stored.setEmail(entity.getEmail());

        return repository.save(stored);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
