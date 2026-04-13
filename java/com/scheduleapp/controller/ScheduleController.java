package com.scheduleapp.controller;

import com.scheduleapp.dto.*;
import com.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    // 생성
    @PostMapping
    public ResponseEntity<CreateScheduleResponse> createSchedule(
            @RequestBody CreateScheduleRequest request
    ) {
        CreateScheduleResponse response = scheduleService.save(request);

        URI location = URI.create("/schedules/" + response.getId());

        return ResponseEntity.created(location).body(response);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<GetScheduleResponse>> getSchedules(
            @RequestParam(required = false) String writer
    ) {
        return ResponseEntity.ok(scheduleService.findAll(writer));
    }

    // 단 건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findOne(scheduleId));
    }

    // 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, request));
    }

    // 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestParam String password
    ) {
        scheduleService.deleteSchedule(scheduleId, password);

        return ResponseEntity.noContent().build();
    }
}
