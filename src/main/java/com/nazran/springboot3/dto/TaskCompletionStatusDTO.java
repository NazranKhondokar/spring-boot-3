package com.nazran.springboot3.dto;

import com.nazran.springboot3.enums.TaskCompletionStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskCompletionStatusDTO {
    private Long entityId;
    private TaskCompletionStatus taskCompletionStatus;
}
