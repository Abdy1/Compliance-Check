package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Identification {
//@Id
//@GeneratedValue(strategy = GenerationType.UUID)
//    private String  Id;
@JacksonXmlProperty
@Column(name = "remarkIdentification",length = 2048)
private String remark;
@JacksonXmlProperty(isAttribute = true)
@Column(length = 2048)
private String diplomatic;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
private String knownExpired;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
private String knownFalse;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
private String reportedLost;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
private String revokedByIssuer;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String eportedLost;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String issuedBy;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String latinNumber;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String   nameOnDocument;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String number;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "regionInIdentification",length = 2048)
    private String region;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String identificationTypeCode;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String identificationTypeDescription;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "countryIsoToCodeInIdentification",length = 2048)
    private String  countryIso2Code;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "countryIdentificationDescription",length = 2048)
    private String countryDescription;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "regulationLanguageInIdentification",length = 2048)
    private String regulationLanguage;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "IdentificationLogicalId")
    private String logicalId;
    @JacksonXmlProperty
   @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "numberTitle", column = @Column(name = "numbeTitle")),
            @AttributeOverride( name = "publicationDate", column = @Column(name = "publicationDateInIdentification")),
            @AttributeOverride(name = "publicationUrl", column = @Column(name = "publicUrl")),
            @AttributeOverride(name = "regulationType", column = @Column(name = "regulateType"))
    })
    private RegulationSummary regulationSummary;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String validTo;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String validFrom;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String issueDate;
}
