package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class BirthDate {
//    @Id
//    @GeneratedValue(strategy= GenerationType.UUID)
//    private String Id;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String circa;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String calendarType;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "cityInBirthDate")
    private String city;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "zipCodeInBirthDate")
    private String zipCode;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String year;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "regionBirthDate",length = 2048)
    private String region;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "placeInBirthDate")
    private String place;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "countryIso2CodeBirthDate")
    private String countryIso2Code;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "countryDescriptionInBirthDate")
    private String countryDescription;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "regulationLanguageInBirthDate",length = 2048)
    private String regulationLanguage;
    @JacksonXmlProperty(localName = "logicalId",isAttribute = true)
    @Column(name = "birthDateLogicalId", length = 2048)
    private String logicalId;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String birthdate;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String dayOfMonth;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String monthOfYear;

    @JacksonXmlProperty
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "numberTitle", column = @Column(name = "numbeTitleBirthDate")),
            @AttributeOverride( name = "publicationDate", column = @Column(name = "publicationDateInBirthDate")),
            @AttributeOverride(name = "publicationUrl", column = @Column(name = "publicationUrlBirthDate")),
            @AttributeOverride(name = "regulationType", column = @Column(name = "regulationTypeBirthDate"))
    })
    private RegulationSummary regulationSummary;
    @Column(length = 2048, name = "birthDateRemark")
    @JacksonXmlProperty
    private String remark;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String yearRangeFrom;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String yearRangeTo;

}
