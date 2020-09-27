package com.Datamatics.ranjithmg.backend.service;

import com.Datamatics.ranjithmg.backend.model.Company;
import com.Datamatics.ranjithmg.backend.model.Contact;
import com.Datamatics.ranjithmg.backend.model.DataDemo;
import com.Datamatics.ranjithmg.backend.repository.CompanyRepository;
import com.Datamatics.ranjithmg.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataService {

    @Autowired
    private Company company;
    @Autowired
    private Contact contact;
    @Autowired
    private DataDemo dataDemo;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private EntityManager entityManager;


    public List<Company> getData() {
       List<Company> companyList = companyRepository.getDataLimit(100);
//        List<Company> companyList = companyRepository.findAll();
       return  companyList;
    }

    public List<DataDemo> getAllData(long i) {
        List<DataDemo> dataDemoList = companyRepository.getAllData(i);
        return dataDemoList;
    }

    public List<DataDemo> getRequestedData(DataDemo dataDemo) {
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
        if(dataDemo.getContactCountry()!=null && !dataDemo.getContactCountry().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getContactCountry().split(",")));
           query.setParameter("countries", myList);
        }
        if(dataDemo.getJobFunction1()!=null && !dataDemo.getJobFunction1().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getJobFunction1().split(",")));
            query.setParameter("function" , myList);
        }
        if(dataDemo.getJobLevel1()!=null && !dataDemo.getJobLevel1().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getJobLevel1().split(",")));
            query.setParameter("level",myList);
        }
        if(dataDemo.getIndustryType1()!=null && !dataDemo.getIndustryType1().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getIndustryType1().split(",")));
            query.setParameter("subTndustry",myList);
        }
        if(dataDemo.getFirstName()!=null && !dataDemo.getFirstName().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getFirstName().split(",")));
            query.setParameter("FirstNames",myList);
        }
        if(dataDemo.getLastName()!=null && !dataDemo.getLastName().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getLastName().split(",")));
            query.setParameter("LastNames",myList);
        }
        if(dataDemo.getSpeciality()!=null && !dataDemo.getSpeciality().equals("")){
            List<String> myList = new ArrayList<String>(Arrays.asList(dataDemo.getSpeciality().split(",")));
            query.setParameter("companies",myList);
        }
        query.setMaxResults(1000);
        List<DataDemo> co = query.getResultList();
        System.out.println(co);
        return co;
    }

    private String getWhereClauseInfo(DataDemo dataDemo) {

        String Where =" ";
        System.out.println(dataDemo);
        if(dataDemo.getContactCountry()!=null && !dataDemo.getContactCountry().equals("")){
            Where = Where +" t.ContactCountry in :countries and ";
        }
        if(dataDemo.getJobFunction1()!=null && !dataDemo.getJobFunction1().equals("")){
            Where = Where +" t.JobFunction1 in :function and ";
        }
        if(dataDemo.getJobLevel1()!=null && !dataDemo.getJobLevel1().equals("")){
            Where= Where +" t.JobLevel1 in :level and ";
        }
        if(dataDemo.getSpeciality()!=null && !dataDemo.getSpeciality().equals("")){
            Where= Where +" c.Company in :companies and ";
        }
        if(dataDemo.getFirstName()!=null && !dataDemo.getFirstName().equals("")){
            Where= Where +" t.FirstName in :FirstNames and ";
        }
        if(dataDemo.getLastName()!=null && !dataDemo.getLastName().equals("")){
            Where= Where +" t.LastName in :LastNames and ";
        }
        if(dataDemo.getIndustryType1()!=null && !dataDemo.getIndustryType1().equals("")){
            Where= Where +" c.SubIndustryType1 in :subTndustry and ";
        }
        if (dataDemo.getCompany() != null) {
            String[] empArray = dataDemo.getCompany().split(",");
            Where = Where + " c.EmployeeSizeFromValue>="+empArray[0]+" and c.EmployeeSizeToValue<="+empArray[1]+" and ";
        }
        if (dataDemo.getSubIndustryType1() != null) {
            String[] empArray = dataDemo.getSubIndustryType1().split(",");
            Where = Where + " 25000*c.EmployeeSizeToValue between "+empArray[0]+" and "+empArray[1]+" ";
        }

        return Where;
    }
}
