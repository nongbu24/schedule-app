package com.scheduleapp.repository;

import com.scheduleapp.entity.Schedule;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // 작성자 값을 기준으로 Schedule 목록을 수정일 기준 오름차순으로 조회한다.
    List<Schedule> findByWriter(String writer, Sort sort);
}
