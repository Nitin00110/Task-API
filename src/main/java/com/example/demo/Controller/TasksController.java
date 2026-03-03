package com.example.demo.Controller;

import com.example.demo.DTO.TasksRecordDTO;
import com.example.demo.DTO.TasksSummaryDTO;
import com.example.demo.Service.TasksService;
import jakarta.validation.Valid;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final Logger log = LoggerFactory.getLogger(TasksController.class);
    private final TasksService tasksService;

    TasksController(TasksService tasksService){
        this.tasksService = tasksService;
    }

    @PostMapping
    public ResponseEntity<TasksSummaryDTO> creatingTask(@Valid @RequestBody TasksRecordDTO tasksRecordDTO){
        log.info("Tasks is created with title of {}",tasksRecordDTO.title());
        TasksSummaryDTO tasksSummaryDTO = tasksService.createTask(tasksRecordDTO);
        log.info("Task created with Id of {}",tasksSummaryDTO.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(tasksSummaryDTO);
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<TasksSummaryDTO> findTaskById(@PathVariable Long taskId){
        log.info("Searching for Task by Id of {}",taskId);
        TasksSummaryDTO tasksSummaryDTO = tasksService.findTaskById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(tasksSummaryDTO);
    }

    @GetMapping("/quadrant/{quadrant}")
    public ResponseEntity<Page<TasksSummaryDTO>> getTasksByQuadrant(
            @PathVariable String quadrant,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Fetching tasks for quadrant: {} | Page: {} | Size: {}", quadrant, page, size);

        // Call the service method we just updated
        Page<TasksSummaryDTO> tasksPage = tasksService.findByQuadrant(quadrant, page, size);

        return ResponseEntity.ok(tasksPage);
    }


}
