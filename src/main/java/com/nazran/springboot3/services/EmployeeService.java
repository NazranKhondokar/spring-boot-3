package com.nazran.springboot3.services;

import com.nazran.springboot3.dto.EmployeeDTO;
import com.nazran.springboot3.entities.Employee;
import com.nazran.springboot3.enums.RecordStatus;
import jakarta.transaction.Transactional;

import java.util.Map;
import java.util.Optional;

public interface EmployeeService {

    @Transactional
    Employee save(EmployeeDTO dto);

    Optional<Employee> findByIdAndRecordStatusNot(Long employeeId, RecordStatus deleted);

    @Transactional
    Employee update(Employee employee, EmployeeDTO dto);

    Map<String, Object> search(Integer page, Integer size, String sortBy, String search);

    Optional<Employee> findById(Long entityId);

    @Transactional
    Employee updateRecordStatus(Employee employee);
}
