package com.nazran.springboot3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nazran.springboot3.enums.RecordStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", updatable = false)
    protected Date createdAt;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    protected Date updatedAt;

    @Version
    @Column(name = "RECORD_VERSION")
    private Integer recordVersion;

    //default one, auto increment for each operation like update

    @Enumerated(EnumType.STRING)
    @Column(name = "RECORD_STATUS")
    private RecordStatus recordStatus;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "CREATOR", updatable = false, columnDefinition = "uniqueidentifier")
    private UUID createdBy;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "UPDATOR", columnDefinition = "uniqueidentifier")
    private UUID updatedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}