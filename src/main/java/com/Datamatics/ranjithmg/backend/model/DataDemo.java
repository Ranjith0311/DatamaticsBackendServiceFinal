package com.Datamatics.ranjithmg.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class DataDemo {

    private String FirstName;
    private String LastName;
    private String JobTitle1;
    private String JobLevel1;
    private String JobFunction1;
    private String ContactCountry;
    private String Company;
    private String Speciality;
    private String IndustryType1;
    private String SubIndustryType1;
    private Double EmployeeSizeFromValue;
    private Double EmployeeSizeToValue;
}
