package com.nazran.springboot3.repositories;

import com.nazran.springboot3.entities.Employee;
import com.nazran.springboot3.enums.RecordStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByIdAndRecordStatusNot(Long id, RecordStatus deleted);

    @Query(value = """
            SELECT
                e.*
            FROM
                EMPLOYEE AS e
            WHERE
                e.RECORD_STATUS <> 'DELETED'
                AND ((:search IS NULL
                    OR :search = '' )
                OR LOWER(e.EMPLOYEE_NAME) LIKE LOWER(CONCAT('%', :search , '%'))
                    OR LOWER(e.EMPLOYEE_NAME_BN) LIKE LOWER(CONCAT('%', :search , '%')))
            ORDER BY
                e.EMPLOYEE_ID DESC
            """,
            countQuery = """
                    SELECT COUNT(e.EMPLOYEE_ID) FROM EMPLOYEE AS e
                    WHERE
                    e.RECORD_STATUS <> 'DELETED'
                    AND ((:search IS NULL
                        OR :search = '' )
                    OR LOWER(e.EMPLOYEE_NAME) LIKE LOWER(CONCAT('%', :search , '%'))
                        OR LOWER(e.EMPLOYEE_NAME_BN) LIKE LOWER(CONCAT('%', :search , '%')))
                    """,
            nativeQuery = true)
    Page<Employee> search(String search, Pageable pageable);
}
