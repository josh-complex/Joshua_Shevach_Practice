package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskerDaoTest {

    @Autowired
    TaskerDao dao;

    Task task;

    @Before
    public void setUp() {
        task = new Task();
        task.setTaskDescription("Scrub grout");
        task.setCreateDate(LocalDate.of(2021, 4, 17));
        task.setDueDate(LocalDate.of(2021, 4, 18));
        task.setCategory("chores");
    }

    @Test
    public void shouldAddGetDeleteTask() {
        task = dao.createTask(task);

        Task task2 = dao.getTask(task.getId());

        assertEquals(task, task2);

        dao.deleteTask(task.getId());

        assertNull(dao.getTask(task.getId()));
    }

    @Test
    public void shouldGetAllTasks() {
        dao.createTask(task);
        dao.createTask(task);

        assertEquals(2, dao.getAllTasks().size());
    }

    @Test
    public void shouldGetTasksByCategory() {
        dao.createTask(task);
        dao.createTask(task);

        assertEquals(2, dao.getTasksByCategory("chores").size());
    }

    @Test
    public void shouldUpdateTask() {
        task = dao.createTask(task);

        task.setTaskDescription("Descript");

        dao.updateTask(task);

        task = dao.getTask(task.getId());

        assertNotEquals("Scrub grout", task.getTaskDescription());
    }

    @After
    public void tearDown() {
        dao.getAllTasks().forEach(x -> dao.deleteTask(x.getId()));
    }

}
