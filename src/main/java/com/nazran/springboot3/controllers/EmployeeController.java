package com.nazran.springboot3.controllers;

import com.nazran.springboot3.dto.EmployeeDTO;
import com.nazran.springboot3.dto.RecordStatusDTO;
import com.nazran.springboot3.entities.Employee;
import com.nazran.springboot3.enums.RecordStatus;
import com.nazran.springboot3.helpers.CommonDataHelper;
import com.nazran.springboot3.responses.EmployeeResponse;
import com.nazran.springboot3.services.impl.EmployeeServiceImpl;
import com.nazran.springboot3.utils.PaginatedResponse;
import com.nazran.springboot3.validators.EmployeeValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.nazran.springboot3.constants.MessageConstants.EMPLOYEE_SAVE;
import static com.nazran.springboot3.constants.MessageConstants.EMPLOYEE_UPDATE;
import static com.nazran.springboot3.exceptions.ApiError.fieldError;
import static com.nazran.springboot3.responses.EmployeeResponse.select;
import static com.nazran.springboot3.utils.ResponseBuilder.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
@Tag(name = "Employee API")
public class EmployeeController {

    private final EmployeeServiceImpl service;

    private final EmployeeValidator employeeValidator;

    private final CommonDataHelper commonDataHelper;

    @PostMapping("/save")
    @Operation(summary = "save employee", description = "save employee")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))
    })
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeDTO dto, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(employeeValidator, dto, bindingResult);

        if (bindingResult.hasErrors())
            return badRequest().body(error(fieldError(bindingResult)).getJson());

        Employee employee = service.save(dto);
        return ok(success(select(employee), EMPLOYEE_SAVE).getJson());
    }

    @PutMapping("/update")
    @Operation(summary = "update employee", description = "update employee")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))
    })
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeDTO dto) {

        Optional<Employee> employee = service.findByIdAndRecordStatusNot(dto.getId(), RecordStatus.DELETED);
        if (employee.isEmpty())
            return badRequest().body(error(HttpStatus.NOT_FOUND, "Employee not found with id: " + dto.getId()).getJson());

        Employee updatedEmployee = service.update(employee.get(), dto);
        return ok(success(select(updatedEmployee), EMPLOYEE_UPDATE).getJson());
    }

    @GetMapping("/list")
    @Operation(summary = "show lists of all employee", description = "show lists of all employee")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))
    })
    public ResponseEntity<JSONObject> lists(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                                            @RequestParam(value = "search", defaultValue = "") String search
    ) {

        PaginatedResponse response = new PaginatedResponse();
        Map<String, Object> map = service.search(page, size, sortBy, search);
        List<Employee> employeeList = (List<Employee>) map.get("lists");
        List<EmployeeResponse> responses = employeeList.stream().map(EmployeeResponse::select).toList();
        commonDataHelper.getCommonData(page, size, map, response, responses);
        return ok(paginatedSuccess(response).getJson());
    }

    @GetMapping("/details/{id}")
    @Operation(summary = "find employee by id", description = "find employee by id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))
    })
    public ResponseEntity<JSONObject> details(@PathVariable Long id) {

        Optional<Employee> employee = service.findByIdAndRecordStatusNot(id, RecordStatus.DELETED);

        return employee.map(value -> ok(success(select(value)).getJson())).orElseGet(() ->
                badRequest().body(error(HttpStatus.NOT_FOUND, "Employee not found with id: " + id).getJson()));
    }

    @PutMapping("/update-record-status")
    @Operation(summary = "update Employee record status", description = "update Employee record status")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))
    })
    public ResponseEntity<JSONObject> updateRecordStatus(@Valid @RequestBody RecordStatusDTO dto) {

        Optional<Employee> employee = service.findById(dto.getEntityId());
        if (employee.isEmpty())
            return badRequest().body(error(HttpStatus.NOT_FOUND, "Employee not found with id: " + dto.getEntityId()).getJson());

        employee.get().setRecordStatus(dto.getRecordStatus());

        return ok(success(select(service.updateRecordStatus(employee.get())), "Employee has " + dto.getRecordStatus()).getJson());
    }
}
