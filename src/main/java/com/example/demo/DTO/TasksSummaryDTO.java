package com.example.demo.DTO;

import com.example.demo.Entities.Tasks;

public record TasksSummaryDTO(
        String title,
        String description,
        int urgency,
        int importance,
        Long id) {

    public static TasksSummaryDTO fromEntity(Tasks tasks){
        return new TasksSummaryDTO(tasks.getTitle(),
         tasks.getDescription(),
         tasks.getUrgency(),
         tasks.getImportance(),
         tasks.getId());
    }
}
