package com.bridgelabz.springaop.services;

import javax.transaction.Transactional;

import com.bridgelabz.springaop.entities.TaskDetails;
import com.bridgelabz.springaop.handler.TaskException;
import com.bridgelabz.springaop.mapper.TaskMapper;
import com.bridgelabz.springaop.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TaskRepository taskRepository;

    @Override
    @Transactional
    public Task createTask(Task task) {

        TaskDetails taskDetails = taskMapper.convertToTaskDetails(task);

        return taskMapper.convertToTaskDto(taskRepository.save(taskDetails));

    }

    @Override
    @Transactional
    public void deleteTask(Long id){
        Optional<TaskDetails> taskDetails=taskRepository.findById(id);

        if(!taskDetails.isPresent()) {
            throw new TaskException(HttpStatus.NOT_FOUND,"Task Not Found!");
        }

        TaskDetails task=taskDetails.get();
        //task.setTaskStatus(TaskStatus.DELETED);
        taskRepository.delete(task);


    }

    @Override
    public Task getTasks(Long id) {
        Optional<TaskDetails> taskDetails=taskRepository.findById(id);

        if(taskDetails.isPresent()) {
            return taskMapper.convertToTaskDto(taskDetails.get());
        }
        return null;
    }

    @Override
    @Transactional
    public Task updateTask(Long id, Task task){
        Optional<TaskDetails> taskDetails=taskRepository.findById(id);

        if(taskDetails.isPresent()) {
            TaskDetails taskDetailsUpdate=taskMapper.convertToTaskDetails(task);
            taskDetailsUpdate.setId(id);
            return taskMapper.convertToTaskDto(taskRepository.save(taskDetailsUpdate));
        }

        throw new TaskException(HttpStatus.NOT_FOUND, "No Task Found with Id "+id);
    }

    @Override
    public List<Task> getAllTasks() {

        return taskMapper.convertToTaskList(taskRepository.findAll());
    }

}
