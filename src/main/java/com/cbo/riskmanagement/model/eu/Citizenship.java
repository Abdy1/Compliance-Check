package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Citizenship {
//    @Id
//    @GeneratedValue(strategy= GenerationType.UUID)
//    private String Id;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "regionInCitizenship")
    private String region;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "countryIso2CodeInCitizenShip")
    private String countryIso2Code;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "countryDescriptionInCitizenship",length = 2048)
    private String countryDescription;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "regulationLanguageinCitizenship")
    private String regulationLanguage;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "citizenShipLogicalId", length = 2048)
    private String logicalId;
    @JacksonXmlProperty
   @Embedded
   @AttributeOverrides({
           @AttributeOverride( name = "numberTitle", column = @Column(name = "numberTitleCitizenship")),
           @AttributeOverride( name = "publicationDate", column = @Column(name = "publicationDateInCitizenship")),
           @AttributeOverride(name = "publicationUrl", column = @Column(name = "publicUrlCitizenship")),
           @AttributeOverride(name = "regulationType", column = @Column(name = "regulateTypeCitizenship"))
   })
    private RegulationSummary regulationSummary;
    @JacksonXmlProperty
    @Column(name = "remarkCitizenship",length = 2048)
    private String remark;

}
