package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnIndividual {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "DATAID")
    @Column(length = 2048)
    private String dataId;
    @JacksonXmlProperty(localName = "VERSIONNUM")
    @Column(length = 2048)
    private String VersionNum;
    @JacksonXmlProperty(localName = "FIRST_NAME")
    @Column(length = 2048)
    private String firstName;
    @JacksonXmlProperty(localName = "SECOND_NAME")
    @Column(length = 2048)
    private String secondName;
    @JacksonXmlProperty(localName = "THIRD_NAME")
    @Column(length = 2048)
    private String thirdName;
    @JacksonXmlProperty(localName = "FOURTH_NAME")
    @Column(length = 2048)
    private String fourthName;
    @JacksonXmlProperty(localName = "UN_LIST_TYPE")
    @Column(length = 2048)
    private String unListType;
    @JacksonXmlProperty(localName = "REFERENCE_NUMBER")
    @Column(length = 2048)
    private String referenceNumber;
    @JacksonXmlProperty(localName = "LISTED_ON")
    @Column(length = 2048)
    private String listedOn;
    @JacksonXmlProperty(localName = "COMMENTS1")
    @Column(length = 2048)
    private String comments1;
    @JacksonXmlProperty(localName = "DESIGNATION")
    @OneToOne(cascade = CascadeType.ALL)
    private Designation designation;
    @JacksonXmlProperty(localName = "INDIVIDUAL_PLACE_OF_BIRTH")
    @Column(length = 2048)
    private String individualPlaceOfBirth;
    @JacksonXmlProperty(localName = "SORT_KEY")
    @Column(length = 2048)
    private String sortKey;
    @JacksonXmlProperty(localName = "SORT_KEY_LAST_MOD")
    @Column(length = 2048)
    private String sortKeyLastMod;
    @JacksonXmlProperty(localName = "INDIVIDUAL_ADDRESS")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private IndividualAddress individualAddress;
    @JacksonXmlProperty(localName = "INDIVIDUAL_ALIAS")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private IndividualAlias individualAlias;
    @JacksonXmlProperty(localName = "INDIVIDUAL_DATE_OF_BIRTH")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private IndividualDateOfBirth individualDateOfBirth;
    @JacksonXmlProperty(localName = "INDIVIDUAL_DOCUMENT")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private IndividualDocument individualDocument;
    @JacksonXmlProperty(localName = "LAST_DAY_UPDATED")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private LastDayUpdated lastDayUpdated;
    @JacksonXmlProperty(localName = "LIST_TYPE")
    @Column(length = 2048)
    private String listType;
    @JacksonXmlProperty(localName = "NATIONALITY")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Nationality nationality;
    @JacksonXmlProperty(localName = "NAME_ORIGINAL_SCRIPT")
    @Column(length = 2048)
    private String nameOriginalScript;
    @JacksonXmlProperty(localName = "GENDER")
    private String gender;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "TITLE")
    private String title;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "SUBMITTED_BY")
    private String submittedBy;

}
