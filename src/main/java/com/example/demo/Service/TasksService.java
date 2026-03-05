package com.example.demo.Service;

import com.example.demo.DTO.TasksRecordDTO;
import com.example.demo.DTO.TasksSummaryDTO;
import com.example.demo.Entities.Tasks;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repository.TasksRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksService {

    private final Logger log = LoggerFactory.getLogger(TasksService.class);
    private final TasksRepo tasksRepo;
    TasksService(TasksRepo tasksRepo){
        this.tasksRepo= tasksRepo;
    }

    public TasksSummaryDTO createTask(TasksRecordDTO tasksRecordDTO){
        log.info("Task Creation Started");
        log.debug("Task details payload: title={}, urgency={}, importance={}",
                tasksRecordDTO.title(), tasksRecordDTO.urgent(), tasksRecordDTO.importance());
        Tasks tasks = new Tasks(
                tasksRecordDTO.title(),
                tasksRecordDTO.description(),
                tasksRecordDTO.urgent(),
                tasksRecordDTO.importance()
        );

        if(("DO_First").equals(tasks.getQuadrant())){
            log.warn("SIMULATED PUSH NOTIFICATION SENT: Urgent & Important task created -> {}", tasks.getTitle());
        }
        return TasksSummaryDTO.fromEntity(tasksRepo.save(tasks));
    }

    public TasksSummaryDTO findTaskById(Long id){
        // Replace IllegalArgumentException with ResourceNotFoundException
        Tasks tasks = tasksRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task Id not Found"));
        return TasksSummaryDTO.fromEntity(tasks);
    }

    public Page<TasksSummaryDTO> findByQuadrant(String quadrant,int pageNumber,int size){

        String formattedQuadrant = quadrant.toUpperCase();

        Pageable pageable = PageRequest.of(pageNumber,size);
        Page<Tasks> tasksOrder = tasksRepo.findByQuadrant(formattedQuadrant,pageable);

        return tasksOrder.map(TasksSummaryDTO::fromEntity);
    }


}
