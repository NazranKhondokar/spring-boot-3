package com.nazran.springboot3.responses;

import com.nazran.springboot3.entities.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EmployeeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String employeeName;

    private String employeeNameBn;

    public static EmployeeResponse select(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setEmployeeName(employee.getEmployeeName());
        response.setEmployeeNameBn(employee.getEmployeeNameBn());
        return response;
    }
}
