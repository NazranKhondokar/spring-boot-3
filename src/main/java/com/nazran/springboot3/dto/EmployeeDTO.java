package com.nazran.springboot3.dto;

import com.nazran.springboot3.entities.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String employeeName;

    private String employeeNameBn;

    public Employee to() {
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        employee.setEmployeeNameBn(employeeNameBn);
        return employee;
    }

    public void update(Employee employee) {
        employee.setEmployeeName(employeeName);
        employee.setEmployeeNameBn(employeeNameBn);
    }
}
