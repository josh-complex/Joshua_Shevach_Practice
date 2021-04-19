package com.trilogyed.tasker.controller;

import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
@RefreshScope
public class TaskerController {

    TaskerService service;

    @Autowired
    public TaskerController(TaskerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskViewModel createTask(@Valid @RequestBody Task task) {
        return service.newTask(task);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskViewModel getTask(@PathVariable Integer id) {
        return service.fetchTask(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskViewModel> getTasks(@RequestParam(required = false) String category) {
        if(category != null) {
            return service.fetchTasksByCategory(category);
        }
        return service.fetchAllTasks();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id) {
        service.deleteTask(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@Valid @RequestBody Task task) {
        service.updateTask(task);
    }


}
