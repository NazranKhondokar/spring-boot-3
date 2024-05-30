package com.nazran.todo.dto;

import com.nazran.todo.enums.TaskCompletionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskCompletionStatusDTO {
    private Long entityId;
    private TaskCompletionStatus taskCompletionStatus;
}
