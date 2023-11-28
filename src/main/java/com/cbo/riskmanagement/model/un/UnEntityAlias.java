package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnEntityAlias {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    @JacksonXmlProperty(localName = "QUALITY")
    @Column(length = 2048)
    private String quality;
    @JacksonXmlProperty(localName = "ALIAS_NAME")
    @Column(length = 2048)
    private String aliasName;
    @JacksonXmlProperty(localName = "NOTE")
    @Column(length = 2048)
    private String note;
    @JacksonXmlProperty(localName = "DATE_OF_BIRTH")
    @Column(length = 2048)
    private String dateOfBirth;
    @JacksonXmlProperty(localName = "CITY_OF_BIRTH")
    @Column(length = 2048)
    private String cityOfBirth;
    @JacksonXmlProperty(localName = "COUNTRY_OF_BIRTH")
    @Column(length = 2048)
    private String countryOfBirth;
}
