package me.mamede.tester_shop_ui.service.impl;

import lombok.AllArgsConstructor;
import me.mamede.tester_shop_ui.entity.ScheduleEntity;
import me.mamede.tester_shop_ui.repository.IScheduleRepository;
import me.mamede.tester_shop_ui.service.IScheduleService;
import me.mamede.tester_shop_ui.service.query.IScheduleQueryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {

    private IScheduleRepository repository;
    private IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());

        return repository.save(entity);
    }

    @Override
    public void delete(long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
