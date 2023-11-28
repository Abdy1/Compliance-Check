package com.cbo.riskmanagement.model;

import com.cbo.riskmanagement.model.eu.NameAlias;
import lombok.Data;

import java.util.List;

@Data
public class DataModel {
    private String Id;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String WholeName;
    private String NameInAhm;
    private String LogicalId;
    private String Remark;
    private String nameLanguage;
    private String publicationUrl;
    private String publicationDate;
    private String passportNumber;
    private String identificationType;
    private String identificationTypeCode;
    private String country;
    private String gender;
    private String dateOfBirth;
    private String from;
    private String uniqueId;
    private String foreignKeyId;
}
