package com.example.task.manager.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class Task {
    private Long id;
    private String name;
    private String deadlineDate;
    private boolean isCompleted;
}