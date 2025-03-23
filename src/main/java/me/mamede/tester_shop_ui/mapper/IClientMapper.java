package me.mamede.tester_shop_ui.mapper;

import jakarta.validation.Valid;
import me.mamede.tester_shop_ui.controller.request.SaveClientRequest;
import me.mamede.tester_shop_ui.controller.request.UpdateClientRequest;
import me.mamede.tester_shop_ui.controller.response.ClientDetailResponse;
import me.mamede.tester_shop_ui.controller.response.ListClientResponse;
import me.mamede.tester_shop_ui.controller.response.SaveClientResponse;
import me.mamede.tester_shop_ui.controller.response.UpdateClientResponse;
import me.mamede.tester_shop_ui.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.springframework.http.HttpStatus.CREATED;

@Mapper(componentModel = SPRING)
public interface IClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    ClientEntity toEntity(final long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);
}
