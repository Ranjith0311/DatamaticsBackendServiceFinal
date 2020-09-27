package com.Datamatics.ranjithmg.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Entity
//@Embeddable
@Table(name = "contact")
@ToString
public class Contact {

    @Column(name="CompanyId")
    private long CompanyId;
    @Id
    @Column(name="index")
    private long index;
    @Column(name="firstname")
    private String FirstName;
    @Column(name="lastname")
    private String LastName;
    @Column(name="jobtitle1")
    private String JobTitle1;
    @Column(name="joblevel1")
    private String JobLevel1;
    @Column(name="jobfunction1")
    private String JobFunction1;
    @Column(name = "\"contact country\"")
    private String ContactCountry;
}
