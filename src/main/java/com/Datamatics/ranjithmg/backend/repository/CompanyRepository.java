package com.Datamatics.ranjithmg.backend.repository;

import com.Datamatics.ranjithmg.backend.model.Company;
import com.Datamatics.ranjithmg.backend.model.DataDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.Datamatics.ranjithmg.backend.model.Contact;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select c from company c where c.index<=?1")
    List<Company> getDataLimit(long i);

    @Query("select new com.Datamatics.ranjithmg.backend.model.DataDemo" +
            "(t.FirstName,t.LastName,t.JobTitle1,t.JobLevel1,t.JobFunction1,t.ContactCountry,c.Company,c.Speciality,c.IndustryType1,c.SubIndustryType1,c.EmployeeSizeFromValue,c.EmployeeSizeToValue)  " +
            "from company c join com.Datamatics.ranjithmg.backend.model.Contact t on c.index=t.index where c.index<=?1")
    List<DataDemo> getAllData(long i);
}
