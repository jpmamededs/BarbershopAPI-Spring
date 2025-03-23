package me.mamede.tester_shop_ui.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.mamede.tester_shop_ui.controller.request.SaveClientRequest;
import me.mamede.tester_shop_ui.controller.request.UpdateClientRequest;
import me.mamede.tester_shop_ui.controller.response.ClientDetailResponse;
import me.mamede.tester_shop_ui.controller.response.ListClientResponse;
import me.mamede.tester_shop_ui.controller.response.SaveClientResponse;
import me.mamede.tester_shop_ui.controller.response.UpdateClientResponse;
import me.mamede.tester_shop_ui.entity.ClientEntity;
import me.mamede.tester_shop_ui.mapper.IClientMapper;
import me.mamede.tester_shop_ui.service.IClientService;
import me.mamede.tester_shop_ui.service.query.IClientQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private IClientService service;
    private IClientQueryService queryService;
    private IClientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(ClientEntity entity);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdateClientRequest request){
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final Long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListClientResponse> list(){
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }


}
