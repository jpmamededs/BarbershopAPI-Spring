package me.mamede.tester_shop_ui.service.query.impl;

import lombok.AllArgsConstructor;
import me.mamede.tester_shop_ui.entity.ScheduleEntity;
import me.mamede.tester_shop_ui.exception.NotFoundException;
import me.mamede.tester_shop_ui.exception.ScheduleInUseException;
import me.mamede.tester_shop_ui.repository.IScheduleRepository;
import me.mamede.tester_shop_ui.service.query.IScheduleQueryService;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(long id) {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("Agendamento não encontrado"));
    }

    @Override
    public List<ScheduleEntity> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThenEqualOrderByStartAtEndAt(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt) {

        if(repository.existsStartAtAndEndAt(startAt, endAt)){
            var message = "Já existe um cliente agendado no horário solicitado";
            throw new ScheduleInUseException(message);
        }

    }
}
