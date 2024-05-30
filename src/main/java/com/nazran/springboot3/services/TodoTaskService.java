package com.nazran.springboot3.services;

import com.nazran.springboot3.dto.TodoTaskDTO;
import com.nazran.springboot3.entities.TodoTask;
import com.nazran.springboot3.enums.RecordStatus;
import jakarta.transaction.Transactional;

import java.util.Map;
import java.util.Optional;

public interface TodoTaskService {

    @Transactional
    TodoTask save(TodoTaskDTO dto);

    Optional<TodoTask> findByIdAndRecordStatusNot(Long todoTaskId, RecordStatus deleted);

    @Transactional
    TodoTask update(TodoTask todoTask, TodoTaskDTO dto);

    Map<String, Object> search(Integer page, Integer size, String sortBy, String search);

    Optional<TodoTask> findById(Long entityId);

    @Transactional
    void delete(TodoTask todoTask);

    @Transactional
    TodoTask updateRecordStatus(TodoTask todoTask);

    @Transactional
    TodoTask updateTaskCompletionStatus(TodoTask todoTask);
}
