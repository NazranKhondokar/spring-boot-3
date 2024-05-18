package com.nazran.springboot3.entities;

import com.nazran.springboot3.enums.Gender;
import com.nazran.springboot3.enums.Nationality;
import com.nazran.springboot3.enums.Religion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "EMPLOYEE")
@NoArgsConstructor
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "EMPLOYEE_NAME", columnDefinition = "nvarchar(255)")
    private String employeeName;

    @Column(name = "EMPLOYEE_NAME_BN", columnDefinition = "nvarchar(255)")
    private String employeeNameBn;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "NID", columnDefinition = "nvarchar(20)")
    private String nid;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 20)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "NATIONALITY", length = 50)
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "RELIGION", length = 20)
    private Religion religion;

    @Column(name = "MOBILE_NO", columnDefinition = "nvarchar(100)")
    private String mobileNo;

    @Column(name = "EMAIL_PERSONAL", columnDefinition = "nvarchar(150)")
    private String emailPersonal;

    @Column(name = "IS_ANY_FAMILY_PHYSICALLY_HANDICAPPED")
    private Boolean isAnyFamilyPhysicallyHandicapped;

    @Column(name = "IS_ETHNIC_GROUP")
    private Boolean isEthnicGroup;

    @Column(name = "IS_FREEDOM_FIGHTER")
    private Boolean isFreedomFighter;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @Column(name = "SIGNATURE_URL")
    private String signatureUrl;

    @Column(name = "NID_URL")
    private String nidUrl;

    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE")
    private Date registrationDate;
}


