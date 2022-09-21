package com.example.demo.services;


import com.example.demo.entities.Task;
import com.example.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;

    // Para Listar
    @Override
    public List<Task> findAll(){
        return (List<Task>) taskRepository.findAll();
    }

    // Para guardar
    @Override
    public Task save(Task tarea) {
        return taskRepository.save(tarea);
    }

    // Para econtrar por id
    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    protected void mapTask(Task from,Task to) {
        to.setDescription(from.getDescription());
        to.setDone(from.getDone());
        to.setDueDate(from.getDueDate());
    }

    @Override
    public Task update(Task fromTask){
        Task toTask = findById(fromTask.getId());
        mapTask(fromTask, toTask);
        return taskRepository.save(toTask);
    }


    // Para eliminar
    @Override
    public void delete (Long id) {
        taskRepository.deleteById(id);
    }
}
