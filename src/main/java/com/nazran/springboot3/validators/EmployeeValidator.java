package com.nazran.springboot3.validators;

import com.nazran.springboot3.dto.EmployeeDTO;
import com.nazran.springboot3.services.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

    private final EmployeeServiceImpl service;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDTO dto = (EmployeeDTO) target;
    }
}
