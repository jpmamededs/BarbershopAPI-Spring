package me.mamede.tester_shop_ui.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.mamede.tester_shop_ui.controller.request.SaveScheduleRequest;
import me.mamede.tester_shop_ui.controller.response.SaveScheduleResponse;
import me.mamede.tester_shop_ui.controller.response.ScheduleAppointmentMonthResponse;
import me.mamede.tester_shop_ui.mapper.IScheduleMapper;
import me.mamede.tester_shop_ui.service.IScheduleService;
import me.mamede.tester_shop_ui.service.query.IScheduleQueryService;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

import static java.time.ZoneOffset.UTC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("schedules")
@AllArgsConstructor
public class ScheduleController {

    private IScheduleService service;
    private IScheduleQueryService queryService;
    private IScheduleMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveScheduleResponse save(@RequestBody @Valid SaveScheduleRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{year}/{month}")
    ScheduleAppointmentMonthResponse listMonth(@PathVariable final int year, @PathVariable final int month){
        var yearMonth = YearMonth.of(year, month);
        var startAt = YearMonth.atDay(1).atTime(0, 0, 0, 0).atOffset(UTC);
        var endAt = YearMonth.atEndOfMonth().atTime(23, 59, 59, 999_999_999);
        var entities = queryService.findInMonth(startAt, endAt);
        return mapper.toMonthResponse(year, month, entities);

    }

}
