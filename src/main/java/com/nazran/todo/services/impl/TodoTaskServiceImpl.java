package com.nazran.todo.services.impl;

import com.nazran.todo.dto.TodoTaskDTO;
import com.nazran.todo.entities.TodoTask;
import com.nazran.todo.enums.RecordStatus;
import com.nazran.todo.repositories.TodoTaskRepository;
import com.nazran.todo.services.TodoTaskService;
import com.nazran.todo.utils.ServiceHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoTaskServiceImpl implements TodoTaskService {

    private final TodoTaskRepository repository;

    @Transactional
    public TodoTask save(TodoTaskDTO dto) {
        TodoTask todoTask = dto.to();
        todoTask.setRecordStatus(RecordStatus.ACTIVE);
        return repository.save(todoTask);
    }

    public Optional<TodoTask> findByIdAndRecordStatusNot(Long todoTaskId, RecordStatus deleted) {
        return repository.findByIdAndRecordStatusNot(todoTaskId, deleted);
    }

    @Transactional
    public TodoTask update(TodoTask todoTask, TodoTaskDTO dto) {
        dto.update(todoTask);
        return repository.save(todoTask);
    }

    public Map<String, Object> search(Integer page, Integer size, String sortBy, String search) {
        ServiceHelper<TodoTask> serviceHelper = new ServiceHelper<>(TodoTask.class);
        return serviceHelper.getList(
                repository.search(search, serviceHelper.getPageable(sortBy, page, size)),
                page, size);
    }

    public Optional<TodoTask> findById(Long entityId) {
        return repository.findById(entityId);
    }

    @Transactional
    public void delete(TodoTask todoTask) {
        repository.delete(todoTask);
    }

    @Transactional
    public TodoTask updateRecordStatus(TodoTask todoTask) {
        return repository.save(todoTask);
    }

    @Transactional
    public TodoTask updateTaskCompletionStatus(TodoTask todoTask) {
        return repository.save(todoTask);
    }
}
