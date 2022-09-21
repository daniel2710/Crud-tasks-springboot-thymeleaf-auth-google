package com.example.demo.controllers;

import com.example.demo.entities.Task;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {


    @Autowired
    TaskService taskService;

    private void baseAttributer(Model model, Task task) {

        model.addAttribute("taskList", taskService.findAll());

        model.addAttribute("taskForm", task);
    }

    // Get vista - List Tasks
    @GetMapping("/tasks")
    public String tasks(Model model){
        baseAttributer(model, new Task());
        return "tasks";
    }

    // Get vista - New Task
    @GetMapping("/tasks/new")
    public String newTask(Model model, Task task){
        baseAttributer(model, new Task());
        return "new-task";
    }

    // Post Form - New Task
    @PostMapping("/task/new")
    public String createTask(@ModelAttribute("taskForm") Task task, BindingResult result, Model model) {

        if (result.hasErrors()) {
            baseAttributer(model, task);
        } else {
            try {
                taskService.save(task);
                baseAttributer(model, new Task());

            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                baseAttributer(model, task);
            }
        }

        return "new-task";
    }

    // Get Vista - Edit Task
    @GetMapping("/editTask/{id}")
    public String editTask(Model model, Task task, @PathVariable(name = "id") Long id) {
        Task taskToEdit = taskService.findById(id);
        baseAttributer(model, taskToEdit);
        return "update";
    }


    // Post Form - Edit Task
    @PostMapping("/editTask")
    public String postEditTask(@ModelAttribute("taskForm") Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            baseAttributer(model, task);
        } else {
            try {
                taskService.update(task);
                baseAttributer(model, new Task());
            } catch (Exception e) {
                model.addAttribute("formErrorMessage", e.getMessage());
                baseAttributer(model, task);
            }
        }
        return "/tasks";
    }

    // Delete
    @GetMapping("/deleteTask/{id}")
    public String deleteTask(Model model, @PathVariable(name="id") Long id){
        taskService.delete(id);
        return "tasks";
    }


}

