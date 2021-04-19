package com.trilogyed.tasker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskerController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class TaskerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskerService service;

    private final ObjectMapper mapper = new ObjectMapper();

    Task inputTask1 = new Task();
    TaskViewModel outputTask1 = new TaskViewModel();

    Task inputTask2 = new Task();
    TaskViewModel outputTask2 = new TaskViewModel();

    List<TaskViewModel> tasks = new ArrayList<>();

    @Before
    public void setUp() {
        inputTask1.setTaskDescription("Scrub grout");
        inputTask1.setCreateDate(LocalDate.of(2021, 4, 17));
        inputTask1.setDueDate(LocalDate.of(2021, 4, 18));
        inputTask1.setCategory("chores");
        outputTask1.setTaskDescription("Scrub grout");
        outputTask1.setCreateDate(LocalDate.of(2021, 4, 17));
        outputTask1.setDueDate(LocalDate.of(2021, 4, 18));
        outputTask1.setCategory("chores");
        outputTask1.setAdvertisement("AD");

        inputTask2.setTaskDescription("Clean gutters");
        inputTask2.setCreateDate(LocalDate.of(2021, 4, 17));
        inputTask2.setDueDate(LocalDate.of(2021, 4, 18));
        inputTask2.setCategory("chores");
        outputTask2.setTaskDescription("Clean gutters");
        outputTask2.setCreateDate(LocalDate.of(2021, 4, 17));
        outputTask2.setDueDate(LocalDate.of(2021, 4, 18));
        outputTask2.setCategory("chores");
        outputTask2.setAdvertisement("AD");

        tasks.add(outputTask1);
        tasks.add(outputTask2);

        when(service.newTask(inputTask1)).thenReturn(outputTask1);
        when(service.fetchTask(1)).thenReturn(outputTask1);
        when(service.fetchAllTasks()).thenReturn(tasks);
        when(service.fetchTasksByCategory("chores")).thenReturn(tasks);
    }

    @Test
    public void shouldAddTaskAndReturnTaskViewModel() throws Exception {
        String inputJson = mapper.writeValueAsString(inputTask1);
        String outputJson = mapper.writeValueAsString(outputTask1);

        mvc.perform(post("/task")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTaskAndReturnTaskViewModel() throws Exception {
        String outputJson = mapper.writeValueAsString(outputTask1);

        mvc.perform(get("/task/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetAllTasksAndReturnListOfTaskViewModels() throws Exception {
        String outputJson = mapper.writeValueAsString(tasks);

        mvc.perform(get("/task"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTasksByCategoryAndReturnListOfTaskViewModels() throws Exception {
        String outputJson = mapper.writeValueAsString(tasks);

        mvc.perform(get("/task")
                .param("category", "chores"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422StatusCodeWhenAttemptingToPostInvalidTask() throws Exception {
        Task task = new Task();
        task.setTaskDescription("Some description");
        String inputJson = mapper.writeValueAsString(task);

        mvc.perform(post("/task")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

}
