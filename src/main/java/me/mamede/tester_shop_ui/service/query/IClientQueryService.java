package me.mamede.tester_shop_ui.service.query;

import me.mamede.tester_shop_ui.entity.ClientEntity;

public interface IClientQueryService {

    ClientEntity findById(final long id);

    List<ClientEntity> list();

    void verifyPhone(final String phone);

    void verifyPhone(final long id, final String phone);

    void verifyEmail(final long id, final String email);

    void verifyEmail(final String phone);
}
