package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.exception.NotFoundException;
import com.trilogyed.tasker.feign.AdserverClient;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class TaskerService {

    TaskerDao dao;
    AdserverClient client;

    @Autowired
    public TaskerService(TaskerDao dao, AdserverClient client) {
        this.dao = dao;
        this.client = client;
    }

    @Transactional
    public TaskViewModel fetchTask(int id) {
        return buildViewModelFromTask(dao.getTask(id));
    }

    public List<TaskViewModel> fetchAllTasks() {
        List<Task> tasks = dao.getAllTasks();
        List<TaskViewModel> tvms = new ArrayList<>();
        tasks.forEach(x -> tvms.add(buildViewModelFromTask(x)));

        if(tvms.isEmpty()) throw new NotFoundException("Tasks not found from task table in caller: " + getClass());

        return tvms;
    }

    public List<TaskViewModel> fetchTasksByCategory(String category) {
        List<Task> tasks = dao.getTasksByCategory(category);
        List<TaskViewModel> tvms = new ArrayList<>();
        tasks.forEach(x -> tvms.add(buildViewModelFromTask(x)));

        if(tvms.isEmpty()) throw new NotFoundException("Tasks with category '" + category + "' not found from task table in caller: " + getClass());

        return tvms;
    }

    @Transactional
    public TaskViewModel newTask(Task task) {
        task.setCategory(task.getCategory().toLowerCase());
        task = dao.createTask(task);

        return buildViewModelFromTask(task);
    }

    public void deleteTask(int id) {
        dao.deleteTask(id);

    }

    public void updateTask(Task task) {
        dao.updateTask(task);
    }

    private TaskViewModel buildViewModelFromTask(Task task) {
        TaskViewModel tvm = new TaskViewModel(
                task.getId(),
                task.getTaskDescription(),
                task.getCreateDate(),
                task.getDueDate(),
                task.getCategory()
        );
        String ad = client.getAd();
        if (ad == null) throw new NotFoundException("Could not fetch ad from adserver in caller: " + getClass());
        tvm.setAdvertisement(ad);

        return tvm;
    }
}
