package com.nazran.springboot3.repositories;

import com.nazran.springboot3.entities.TodoTask;
import com.nazran.springboot3.enums.RecordStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoTaskRepository extends JpaRepository<TodoTask, Long> {

    Optional<TodoTask> findByIdAndRecordStatusNot(Long id, RecordStatus deleted);

    @Query(value = """
            SELECT
                e.*
            FROM
                TODO_TASK AS e
            WHERE
                e.RECORD_STATUS <> 'DELETED'
                AND ((:search IS NULL
                    OR :search = '' )
                OR LOWER(e.TODO_TASK_NAME) LIKE LOWER(CONCAT('%', :search , '%')))
            ORDER BY
                e.TODO_TASK_ID DESC
            """,
            countQuery = """
                    SELECT COUNT(e.TODO_TASK_ID) FROM TODO_TASK AS e
                    WHERE
                    e.RECORD_STATUS <> 'DELETED'
                    AND ((:search IS NULL
                        OR :search = '' )
                    OR LOWER(e.TODO_TASK_NAME) LIKE LOWER(CONCAT('%', :search , '%')))
                    """,
            nativeQuery = true)
    Page<TodoTask> search(String search, Pageable pageable);
}
