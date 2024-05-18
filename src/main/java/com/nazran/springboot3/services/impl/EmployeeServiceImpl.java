package com.nazran.springboot3.services.impl;

import com.nazran.springboot3.dto.EmployeeDTO;
import com.nazran.springboot3.entities.Employee;
import com.nazran.springboot3.enums.RecordStatus;
import com.nazran.springboot3.repositories.EmployeeRepository;
import com.nazran.springboot3.services.EmployeeService;
import com.nazran.springboot3.utils.ServiceHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Transactional
    public Employee save(EmployeeDTO dto) {
        Employee employee = dto.to();
        employee.setRecordStatus(RecordStatus.ACTIVE);
        return repository.save(employee);
    }

    public Optional<Employee> findByIdAndRecordStatusNot(Long employeeId, RecordStatus deleted) {
        return repository.findByIdAndRecordStatusNot(employeeId, deleted);
    }

    @Transactional
    public Employee update(Employee employee, EmployeeDTO dto) {
        dto.update(employee);
        return repository.save(employee);
    }

    public Map<String, Object> search(Integer page, Integer size, String sortBy, String search) {
        ServiceHelper<Employee> serviceHelper = new ServiceHelper<>(Employee.class);
        return serviceHelper.getList(
                repository.search(search, serviceHelper.getPageable(sortBy, page, size)),
                page, size);
    }

    public Optional<Employee> findById(Long entityId) {
        return repository.findById(entityId);
    }

    @Transactional
    public Employee updateRecordStatus(Employee employee) {
        return repository.save(employee);
    }
}
