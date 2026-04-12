package com.scheduleapp.service;

import com.scheduleapp.dto.CreateScheduleRequest;
import com.scheduleapp.dto.CreateScheduleResponse;
import com.scheduleapp.dto.GetScheduleResponse;
import com.scheduleapp.entity.Schedule;
import com.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    // 생성
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getWriter(),
                request.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getWriter(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 전체 일정 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll(String writer) {
        List<Schedule> schedules;

        // 작성자를 입력하지 않았을 때
        if (writer == null) {
            schedules = scheduleRepository.findAll();
        // 작성자를 입력했을 때
        } else {
            schedules = scheduleRepository.findByWriter(writer);
        }
        List<GetScheduleResponse> dtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );

            dtos.add(dto);
        }

        return dtos;
    }

    // 단 건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 영화입니다.")
        );

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
