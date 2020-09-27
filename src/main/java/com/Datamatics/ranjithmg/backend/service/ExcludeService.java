package com.Datamatics.ranjithmg.backend.service;

import com.Datamatics.ranjithmg.backend.model.DataDemo;
import com.Datamatics.ranjithmg.backend.model.DataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExcludeService {

    @Autowired
    private EntityManager entityManager;

    public List<DataDemo> getExcludedData(DataRequest dataDemo) {
        String whereString = getWhereClauseInfo(dataDemo);
        if(whereString.endsWith("and ")){
            whereString = whereString.substring(0,whereString.length()-4);
        }
        System.out.println(whereString);
        Query query = entityManager.createQuery("select new com.Datamatics.ranjithmg.backend.model.DataDemo" +
                "(t.FirstName,t.LastName,t.JobTitle1,t.JobLevel1,t.JobFunction1,t.ContactCountry,c.Company,c.Speciality,c.IndustryType1,c.SubIndustryType1,c.EmployeeSizeFromValue,c.EmployeeSizeToValue) " +
                "from com.Datamatics.ranjithmg.backend.model.Company c join com.Datamatics.ranjithmg.backend.model.Contact t " +
                "on c.index = t.index " +
                "where "+whereString+" ");
        if(dataDemo.getCountry()!=null && !dataDemo.getCountry().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getCountry().split(",")));
            query.setParameter("countries", myList);
        }
        if(dataDemo.getJobFunction()!=null && !dataDemo.getJobFunction().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getJobFunction().split(",")));
            query.setParameter("function" , myList);
        }
        if(dataDemo.getJobLevel()!=null && !dataDemo.getJobLevel().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getJobLevel().split(",")));
            query.setParameter("level",myList);
        }
        if(dataDemo.getSubIndustry()!=null && !dataDemo.getSubIndustry().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getSubIndustry().split(",")));
            query.setParameter("subIndustry",myList);
        }
        if(dataDemo.getCompany()!=null && !dataDemo.getCompany().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getCompany().split(",")));
            query.setParameter("companies",myList);
        }
        if(dataDemo.getName()!=null && !dataDemo.getName().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getName().split(",")));
            query.setParameter("firstNames",myList);
        }
        if(dataDemo.getName()!=null && !dataDemo.getName().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getName().split(",")));
            query.setParameter("lastNames",myList);
        }
        query.setMaxResults(1000);
        List<DataDemo> co = query.getResultList();
        System.out.println(co);
        return co;
    }

    private String getWhereClauseInfo(DataRequest dataDemo) {

            String Where =" ";
            System.out.println(dataDemo);
            if(dataDemo.getCountry()!=null && !dataDemo.getCountry().equals("")){
                Where = Where +" t.ContactCountry not in :countries and ";
            }
            if(dataDemo.getJobFunction()!=null && !dataDemo.getJobFunction().equals("")){
                Where = Where +" t.JobFunction1 not in :function and ";
            }
            if(dataDemo.getJobLevel()!=null && !dataDemo.getJobLevel().equals("")){
                Where = Where +" t.JobLevel1 not in :level and ";
            }
            if(dataDemo.getSubIndustry()!=null && !dataDemo.getSubIndustry().equals("")){
                Where= Where +" c.SubIndustryType1 not in :subIndustry and ";
            }
            if(dataDemo.getCompany()!=null && !dataDemo.getCompany().equals("")){
                Where= Where +" c.Company not in :companies and ";
            }
            if(dataDemo.getName()!=null && !dataDemo.getName().equals("")){
                Where= Where +" t.FirstName not in :firstNames and ";
            }
            if(dataDemo.getName()!=null && !dataDemo.getName().equals("")){
                Where= Where +" t.LastName not in :lastNames and ";
            }
            return Where;
    }
}
