package com.nazran.springboot3.validators;

import com.nazran.springboot3.dto.TodoTaskDTO;
import com.nazran.springboot3.services.impl.TodoTaskServiceImpl;
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
    }
}
