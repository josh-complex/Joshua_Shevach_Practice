package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.feign.AdserverClient;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TaskerServiceTest {

    @Mock
    private TaskerDao dao;

    private TaskerService service;

    @Mock
    private AdserverClient client;

    Task inputTask1 = new Task();
    Task outputTask1 = new Task();

    Task inputTask2 = new Task();
    Task outputTask2 = new Task();

    List<Task> tasks = new ArrayList<>();

    @Before
    public void setUp() {
        inputTask1.setTaskDescription("Scrub grout");
        inputTask1.setCreateDate(LocalDate.of(2021, 4, 17));
        inputTask1.setDueDate(LocalDate.of(2021, 4, 18));
        inputTask1.setCategory("chores");
        outputTask1.setId(1);
        outputTask1.setTaskDescription("Scrub grout");
        outputTask1.setCreateDate(LocalDate.of(2021, 4, 17));
        outputTask1.setDueDate(LocalDate.of(2021, 4, 18));
        outputTask1.setCategory("chores");

        inputTask2.setTaskDescription("Clean gutters");
        inputTask2.setCreateDate(LocalDate.of(2021, 4, 17));
        inputTask2.setDueDate(LocalDate.of(2021, 4, 18));
        inputTask2.setCategory("chores");
        outputTask2.setId(1);
        outputTask2.setTaskDescription("Clean gutters");
        outputTask2.setCreateDate(LocalDate.of(2021, 4, 17));
        outputTask2.setDueDate(LocalDate.of(2021, 4, 18));
        outputTask2.setCategory("chores");

        tasks.add(outputTask1);
        tasks.add(outputTask2);

        when(dao.createTask(inputTask1)).thenReturn(outputTask1);
        when(dao.getTask(1)).thenReturn(outputTask1);
        when(dao.getAllTasks()).thenReturn(tasks);
        when(dao.getTasksByCategory("chores")).thenReturn(tasks);
        when(client.getAd()).thenReturn("Ad");

        service = new TaskerService(dao, client);

    }

    @Test
    public void shouldCreateTaskAndReturnTaskViewModelThenGetSameTaskViewModelFromService() {

        TaskViewModel task = service.newTask(inputTask1);
        TaskViewModel taskGottenFromService = service.fetchTask(1);
        List<TaskViewModel> tasksGottenFromService = service.fetchAllTasks();

        assertEquals(task, taskGottenFromService);
        assertEquals(2, tasksGottenFromService.size());

        verify(client, times(4)).getAd();

    }

}
