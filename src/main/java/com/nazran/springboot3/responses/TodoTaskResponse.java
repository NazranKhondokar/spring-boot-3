package com.nazran.springboot3.responses;

import com.nazran.springboot3.entities.TodoTask;
import com.nazran.springboot3.enums.TaskCompletionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class TodoTaskResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String todoTaskName;
    private String todoTaskDetails;
    private LocalDate todoTaskStartDate;
    private LocalDate todoTaskEndDate;
    private TaskCompletionStatus taskCompletionStatus;

    public static TodoTaskResponse select(TodoTask todoTask) {
        TodoTaskResponse response = new TodoTaskResponse();
        response.setId(todoTask.getId());
        response.setTodoTaskName(todoTask.getTodoTaskName());
        response.setTodoTaskDetails(todoTask.getTodoTaskDetails());
        response.setTodoTaskStartDate(todoTask.getTodoTaskStartDate());
        response.setTodoTaskEndDate(todoTask.getTodoTaskEndDate());
        response.setTaskCompletionStatus(todoTask.getTaskCompletionStatus());
        return response;
    }
}
