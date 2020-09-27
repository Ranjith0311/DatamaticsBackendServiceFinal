package com.Datamatics.ranjithmg.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Entity(name = "company")
//@Embeddable
@Table(name = "company")
@ToString
public class Company {

    @Column(name="companyid")
    private long CompanyId;
    @Id
    @Column(name="index")
    private long index;
    @Column(name="company")
    private String Company;
    @Column(name="speciality")
    private String Speciality;
    @Column(name="industrytype1")
    private String IndustryType1;
    @Column(name="subindustrytype1")
    private String SubIndustryType1;
    @Column(name="employeesizefromvalue")
    private Double EmployeeSizeFromValue;
    @Column(name="employeesizetovalue")
    private Double EmployeeSizeToValue;
}


