package com.nazran.springboot3.dto;

import com.nazran.springboot3.enums.RecordStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordStatusDTO {
    private Long entityId;
    private RecordStatus recordStatus;
}
