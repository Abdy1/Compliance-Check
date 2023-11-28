package com.cbo.riskmanagement.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DeliquentList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;
    @CsvBindByName(column = "LastsysEdit")
    @Column(columnDefinition="text")
    private String LastsysEdit;
    @CsvBindByName(column = "CustomerName")
    @Column(columnDefinition="text")
    private String CustomerName;
    @CsvBindByName(column = "NBEReference")
    @Column( columnDefinition = "text")
    private String NBEReference;
    @CsvBindByName(column = "AccountNumber")
    @Column( columnDefinition = "text")
    private String AccountNumber;
    @CsvBindByName(column = "DateClosed")
    @Column( columnDefinition = "text")
    private String DateClosed;
    @CsvBindByName(column = "Bank")
    @Column( columnDefinition = "text")
    private String Bank;
    @CsvBindByName(column = "Branch")
    @Column( columnDefinition = "text")
    private String Branch;


    @Column( columnDefinition = "text")
    private String MotherName;


}
