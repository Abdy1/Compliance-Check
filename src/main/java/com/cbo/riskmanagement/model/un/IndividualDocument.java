package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class IndividualDocument {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @JacksonXmlProperty(localName = "TYPE_OF_DOCUMENT")
    @Column(length = 2048)
    private String typeOfDocument;
    @JacksonXmlProperty(localName = "NUMBER")
    @Column(length = 2048)
    private String number;
    @JacksonXmlProperty(localName = "ISSUING_COUNTRY")
    @Column(length = 2048)
    private String issuingCountry;
    @JacksonXmlProperty(localName = "STATE_PROVINCE")
    @Column(length = 2048)
    private String stateProvince;
    @JacksonXmlProperty(localName = "NOTE")
    @Column(length = 2048)
    private String note;
    @JacksonXmlProperty(localName = "COUNTRY_OF_ISSUE")
    @Column(length = 2048)
    private String countryOfIssue;
    @JacksonXmlProperty(localName = "TYPE_OF_DOCUMENT2")
    @Column(length = 2048)
    private String typeOfDocument2;
    @JacksonXmlProperty(localName = "DATE_OF_ISSUE")
    @Column(length = 2048)
    private String dateOfIssue;
    @JacksonXmlProperty(localName = "CITY_OF_ISSUE")
    @Column(length = 2048)
    private String cityOfIssue;
}
