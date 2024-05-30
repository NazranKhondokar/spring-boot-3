package com.nazran.springboot3.dto;

import com.nazran.springboot3.entities.TodoTask;
import com.nazran.springboot3.enums.TaskCompletionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class TodoTaskDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String todoTaskName;
    private String todoTaskDetails;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date todoTaskStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date todoTaskEndDate;
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
