package com.nazran.springboot3.entities;

import com.nazran.springboot3.enums.TaskCompletionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "TODO_TASK")
@NoArgsConstructor
public class TodoTask extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_TASK_ID")
    private Long id;

    @Column(name = "TODO_TASK_NAME", columnDefinition = "nvarchar(255)")
    private String todoTaskName;

    @Column(name = "TODO_TASK_DETAILS", columnDefinition = "nvarchar(1000)")
    private String todoTaskDetails;

    @Column(name = "TODO_TASK_START_DATE")
    private LocalDate todoTaskStartDate;

    @Column(name = "TODO_TASK_END_DATE")
    private LocalDate todoTaskEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "TODO_TASK_STATUS")
    private TaskCompletionStatus taskCompletionStatus;
}


