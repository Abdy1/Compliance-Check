package com.cbo.riskmanagement.model;

import com.cbo.riskmanagement.model.uk.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UKResponseDetail {
    private String dateDesignated;
    private String designationSource;
    private String individualEntityShip;
    private String lastUpdated;
    private String oFSIgroupId;
    private String otherInformation;
    private String regimeName;
    private String sanctionImposed;
    private String ukStatementOfReasons;
    private String unReferenceNumber;
    private String uniqueId;
    private List<Location> locationList;
    private List<String> emailAddress;
    private String gender;
    private List<String> iMONumber;
    private List<Name> names;
    private List<String> dobs;
    private List<NationalIdentifier> nationalIdentifierList;
    private List<NonLatinName> nonLatinNameList;
    private List<Passport> passportList;
    private List<String> phoneNumber;
    private List<String> position;
    private List<String> subsidiaryList;
    private List<String> titles;
    private String typeOfEntity;
    private List<UkAddress> ukAddressList;
    private List<String> parentCompanyList;
    private String businessRegistrationNumber;
    private List<String> nationality;
    private List<String> websiteList;

}
