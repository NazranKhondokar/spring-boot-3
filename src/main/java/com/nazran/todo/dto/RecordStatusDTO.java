package com.nazran.todo.dto;

import com.nazran.todo.enums.RecordStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordStatusDTO {
    private Long entityId;
    private RecordStatus recordStatus;
}
