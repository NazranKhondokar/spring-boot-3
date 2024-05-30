package com.nazran.todo.dto;

import com.nazran.todo.entities.TodoTask;
import com.nazran.todo.enums.TaskCompletionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TodoTaskDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String todoTaskName;
    private String todoTaskDetails;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate todoTaskStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate todoTaskEndDate;
    private TaskCompletionStatus taskCompletionStatus;

    public TodoTask to() {
        TodoTask todoTask = new TodoTask();
        todoTask.setTodoTaskName(todoTaskName);
        todoTask.setTodoTaskDetails(todoTaskDetails);
        todoTask.setTodoTaskStartDate(todoTaskStartDate);
        todoTask.setTodoTaskEndDate(todoTaskEndDate);
        todoTask.setTaskCompletionStatus(taskCompletionStatus);
        return todoTask;
    }

    public void update(TodoTask todoTask) {
        todoTask.setTodoTaskName(todoTaskName);
        todoTask.setTodoTaskName(todoTaskName);
        todoTask.setTodoTaskDetails(todoTaskDetails);
        todoTask.setTodoTaskStartDate(todoTaskStartDate);
        todoTask.setTodoTaskEndDate(todoTaskEndDate);
        todoTask.setTaskCompletionStatus(taskCompletionStatus);
    }
}
