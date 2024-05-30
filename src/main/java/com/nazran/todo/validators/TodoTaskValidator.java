package com.nazran.todo.validators;

import com.nazran.todo.dto.TodoTaskDTO;
import com.nazran.todo.exceptions.CustomMessageException;
import com.nazran.todo.services.impl.TodoTaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class TodoTaskValidator implements Validator {

    private final TodoTaskServiceImpl service;

    @Override
    public boolean supports(Class<?> clazz) {
        return TodoTaskDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TodoTaskDTO dto = (TodoTaskDTO) target;

        if (dto.getTodoTaskStartDate().isAfter(dto.getTodoTaskEndDate())) {
            throw new CustomMessageException("Task end date should not less than start date");
        }
    }
}
