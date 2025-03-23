package me.mamede.tester_shop_ui.service;

import me.mamede.tester_shop_ui.entity.ScheduleEntity;

public interface IScheduleService {

    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final long id);

}
