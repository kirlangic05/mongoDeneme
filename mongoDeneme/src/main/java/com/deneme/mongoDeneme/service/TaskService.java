package com.deneme.mongoDeneme.service;

import com.deneme.mongoDeneme.model.Task;
import com.deneme.mongoDeneme.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return  repository.save(task);

    }

    public List<Task> findAllTask(){
        return repository.findAll();
    }

    public Task getTaskByTaskId(String taskId){
return repository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity){
        return repository.findBySeverity(severity);
    }
    public List<Task> getTaskByAssignee(String assignee){
        return repository.findByAssignee(assignee);
    }
    public Task updateTask(Task taskRequest){
        Task existingTask= repository.findById(taskRequest.getTaskId()).get();
        existingTask.setTaskId(taskRequest.getTaskId());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        existingTask.setDescription(taskRequest.getDescription());
        return repository.save(existingTask);

    }
    public String deleteTask(String taskId){
        repository.deleteById(taskId);
        return taskId+" silme işlemi tamamlandı";
    }
}

