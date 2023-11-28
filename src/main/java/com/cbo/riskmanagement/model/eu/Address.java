package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Embeddable
public class Address {
//@Id
//@GeneratedValue(strategy = GenerationType.UUID)
//    private  String Id;
@JacksonXmlProperty(isAttribute = true)
@Column(length = 2048, name = "city_in_address")
    private String city;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String street;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String poBox;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "zipCodeInAddress")
    private String zipCode;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "regionInAddress")
    private String region;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "placeInAddress")
    private String place;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String asAtListingTime;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "countryIso2CodeInAddress")
    private String countryIso2Code;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "countryDescriptionInAddress")
    private String countryDescription;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "regulationLanguageInAddress")
    private String regulationLanguage;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "AddressLogicalId",length = 2048)
    private String logicalId;
    @JacksonXmlProperty
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "numberTitle", column = @Column(name = "numbeTitleAddress")),
            @AttributeOverride( name = "publicationDate", column = @Column(name = "publicationDateInAddress")),
            @AttributeOverride(name = "publicationUrl", column = @Column(name = "publicationUrlAddress")),
            @AttributeOverride(name = "regulationType", column = @Column(name = "regulationTypeAddress"))
    })
    private RegulationSummary regulationSummary;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String contactInfo;
    @JacksonXmlProperty
    @Column(name = "remarkAddress",length = 2048)
    private String remark;

}
