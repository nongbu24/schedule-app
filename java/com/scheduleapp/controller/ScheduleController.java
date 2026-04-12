package com.scheduleapp.controller;

import com.scheduleapp.dto.*;
import com.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    // 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getSchedules(
            @RequestParam(required = false) String writer
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll(writer));
    }

    // 단 건 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

    // 수정
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(scheduleId, request));
    }

    // 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestParam String password
    ) {
        scheduleService.deleteSchedule(scheduleId, password);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
