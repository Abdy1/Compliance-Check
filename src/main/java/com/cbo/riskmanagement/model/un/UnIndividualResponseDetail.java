package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UnIndividualResponseDetail {
    private Long Id;
    private String dataId;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String fourthName;
    private String unListType;
    private String referenceNumber;
    private String listedOn;
    private String comments1;
    private String individualPlaceOfBirth;
    private String sortKey;
    private String sortKeyLastMod;
    private String listType;
    private String nationality;
    private String nameOriginalScript;
    private String gender;
    private String title;
    private String submittedBy;
    private String country;
    private String addressNote;
    private String stateProvince;
    private String street;
    private String city;
    private String zipCode;
    private String quality;
    private String aliasName;
    private String aliasNote;
    private String dateOfBirth;
    private String cityOfBirth;
    private String countryOfBirth;
    private String typeOfDate;
    private String date;
    private String year;
    private String fromYear;
    private String toYear;
    private String birthDateNote;
    private String typeOfDocument;
    private String number;
    private String issuingCountry;
    private String documentStateProvince;
    private String note;
    private String countryOfIssue;
    private String typeOfDocument2;
    private String dateOfIssue;
    private String cityOfIssue;
}
