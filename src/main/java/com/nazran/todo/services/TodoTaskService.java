package com.nazran.todo.services;

import com.nazran.todo.dto.TodoTaskDTO;
import com.nazran.todo.entities.TodoTask;
import com.nazran.todo.enums.RecordStatus;
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
