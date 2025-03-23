package me.mamede.tester_shop_ui.service.query.impl;

import lombok.AllArgsConstructor;
import me.mamede.tester_shop_ui.IClientRepository;
import me.mamede.tester_shop_ui.entity.ClientEntity;
import me.mamede.tester_shop_ui.exception.EmailInUseException;
import me.mamede.tester_shop_ui.exception.NotFoundException;
import me.mamede.tester_shop_ui.exception.PhoneInUseException;
import me.mamede.tester_shop_ui.service.query.IClientQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final IClientRepository repository;

    @Override
    public ClientEntity findById(long id) {
        return repository.findById(id).orElseThrow(()->new NotFoundException("Não foi encontrado o cliente de id" + id));
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        var optional = repository.findByPhone(phone);
        if(optional.isPresent()&& !Objects.equals(optional.get().getPhone(), phone)){
            var message = "O telefone " + phone + "já está em uso";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(long id, String email) {
        var optional = repository.findByEmail(email);
        if(optional.isPresent()&& !Objects.equals(optional.get().getEmail(), email)){
            var message = "O email " + email + "já está em uso";
            throw new EmailInUseException(message);
        }
    }

    @Override
    public void verifyEmail(String phone) {

    }
}
