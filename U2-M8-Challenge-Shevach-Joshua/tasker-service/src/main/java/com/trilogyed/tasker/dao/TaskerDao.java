package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.model.Task;

import java.util.List;

public interface TaskerDao {

    Task createTask(Task task);
    Task getTask(int id);
    List<Task> getAllTasks();
    List<Task> getTasksByCategory(String category);
    void updateTask(Task task);
    void deleteTask(int id);

}
