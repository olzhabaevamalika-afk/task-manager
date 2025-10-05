package com.example.task.manager.controller;

import com.example.task.manager.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "form";
    }

    @PostMapping
    public String addTask(@ModelAttribute Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String viewTask(@PathVariable Long id, Model model) {
        Task found = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("task", found);
        return "details";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Task found = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("task", found);
        return "form";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task updated) {
        for (Task t : tasks) {
            if (t.getId().equals(id)) {
                t.setName(updated.getName());
                t.setDeadlineDate(updated.getDeadlineDate());
                t.setCompleted(updated.isCompleted());
            }
        }
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        tasks.removeIf(t -> t.getId().equals(id));
        return "redirect:/tasks";
    }
}
