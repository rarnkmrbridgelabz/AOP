package com.bridgelabz.springaop.services;

import java.util.List;

import org.springframework.scheduling.config.Task;

public interface TaskService {

    public Task createTask(Task task);
    public void deleteTask(Long id);
    public Task getTasks(Long id);
    public Task updateTask(Long id,Task task);
    public List<Task> getAllTasks();
}
