package com.nazran.springboot3.controllers;

import com.nazran.springboot3.dto.TaskCompletionStatusDTO;
import com.nazran.springboot3.dto.TodoTaskDTO;
import com.nazran.springboot3.dto.RecordStatusDTO;
import com.nazran.springboot3.entities.TodoTask;
import com.nazran.springboot3.enums.RecordStatus;
import com.nazran.springboot3.helpers.CommonDataHelper;
import com.nazran.springboot3.responses.TodoTaskResponse;
import com.nazran.springboot3.services.impl.TodoTaskServiceImpl;
import com.nazran.springboot3.utils.PaginatedResponse;
import com.nazran.springboot3.validators.TodoTaskValidator;
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

import static com.nazran.springboot3.constants.MessageConstants.*;
import static com.nazran.springboot3.exceptions.ApiError.fieldError;
import static com.nazran.springboot3.responses.TodoTaskResponse.select;
import static com.nazran.springboot3.utils.ResponseBuilder.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/todo-task")
@Tag(name = "TodoTask API")
public class TodoTaskController {

    private final TodoTaskServiceImpl service;

    private final TodoTaskValidator todoTaskValidator;

    private final CommonDataHelper commonDataHelper;

    @PostMapping("/save")
    @Operation(summary = "save todoTask", description = "save todoTask")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoTaskResponse.class))
    })
    public ResponseEntity<JSONObject> save(@Valid @RequestBody TodoTaskDTO dto, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(todoTaskValidator, dto, bindingResult);

        if (bindingResult.hasErrors())
            return badRequest().body(error(fieldError(bindingResult)).getJson());

        TodoTask todoTask = service.save(dto);
        return ok(success(select(todoTask), TODO_TASK_SAVE).getJson());
    }

    @PutMapping("/update")
    @Operation(summary = "update todoTask", description = "update todoTask")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoTaskResponse.class))
    })
    public ResponseEntity<JSONObject> update(@Valid @RequestBody TodoTaskDTO dto) {

        Optional<TodoTask> todoTask = service.findByIdAndRecordStatusNot(dto.getId(), RecordStatus.DELETED);
        if (todoTask.isEmpty())
            return badRequest().body(error(HttpStatus.NOT_FOUND, "TodoTask not found with id: " + dto.getId()).getJson());

        TodoTask updatedTodoTask = service.update(todoTask.get(), dto);
        return ok(success(select(updatedTodoTask), TODO_TASK_UPDATE).getJson());
    }

    @GetMapping("/list")
    @Operation(summary = "show lists of all todoTask", description = "show lists of all todoTask")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoTaskResponse.class))
    })
    public ResponseEntity<JSONObject> lists(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
                                            @RequestParam(value = "search", defaultValue = "") String search
    ) {

        PaginatedResponse response = new PaginatedResponse();
        Map<String, Object> map = service.search(page, size, sortBy, search);
        List<TodoTask> todoTaskList = (List<TodoTask>) map.get("lists");
        List<TodoTaskResponse> responses = todoTaskList.stream().map(TodoTaskResponse::select).toList();
        commonDataHelper.getCommonData(page, size, map, response, responses);
        return ok(paginatedSuccess(response).getJson());
    }

    @GetMapping("/details/{id}")
    @Operation(summary = "find todoTask by id", description = "find todoTask by id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoTaskResponse.class))
    })
    public ResponseEntity<JSONObject> details(@PathVariable Long id) {

        Optional<TodoTask> todoTask = service.findByIdAndRecordStatusNot(id, RecordStatus.DELETED);

        return todoTask.map(value -> ok(success(select(value)).getJson())).orElseGet(() ->
                badRequest().body(error(HttpStatus.NOT_FOUND, "TodoTask not found with id: " + id).getJson()));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete todoTask by id", description = "delete todoTask by id")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoTaskResponse.class))
    })
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        Optional<TodoTask> todoTask = service.findByIdAndRecordStatusNot(id, RecordStatus.DELETED);
        if (todoTask.isEmpty()) return badRequest().body(error(HttpStatus.NOT_FOUND, "TodoTask not found with id: " + id).getJson());
        service.delete(todoTask.get());
        return ok(success(null, TODO_TASK_DELETE).getJson());
    }

    /*
    * this for soft delete
    * */
    @PutMapping("/update-record-status")
    @Operation(summary = "update TodoTask record status", description = "update TodoTask record status")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoTaskResponse.class))
    })
    public ResponseEntity<JSONObject> updateRecordStatus(@Valid @RequestBody RecordStatusDTO dto) {

        Optional<TodoTask> todoTask = service.findById(dto.getEntityId());
        if (todoTask.isEmpty())
            return badRequest().body(error(HttpStatus.NOT_FOUND, "TodoTask not found with id: " + dto.getEntityId()).getJson());

        todoTask.get().setRecordStatus(dto.getRecordStatus());

        return ok(success(select(service.updateRecordStatus(todoTask.get())), "TodoTask has " + dto.getRecordStatus()).getJson());
    }

    @PutMapping("/update-task-completion-status")
    @Operation(summary = "update TodoTask completion status", description = "update TodoTask completion status")
    @ApiResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoTaskResponse.class))
    })
    public ResponseEntity<JSONObject> updateTaskCompletionStatus(@Valid @RequestBody TaskCompletionStatusDTO dto) {

        Optional<TodoTask> todoTask = service.findByIdAndRecordStatusNot(dto.getEntityId(), RecordStatus.DELETED);
        if (todoTask.isEmpty())
            return badRequest().body(error(HttpStatus.NOT_FOUND, "TodoTask not found with id: " + dto.getEntityId()).getJson());

        todoTask.get().setTaskCompletionStatus(dto.getTaskCompletionStatus());

        return ok(success(select(service.updateRecordStatus(todoTask.get())), "TodoTask has " + dto.getTaskCompletionStatus()).getJson());
    }
}
