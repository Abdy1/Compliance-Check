package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class IndividualAddress {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "COUNTRY")
    @Column(length = 2048)
    private String country;
    @JacksonXmlProperty(localName = "NOTE")
    @Column(length = 2048)
    private String note;
    @JacksonXmlProperty(localName = "STATE_PROVINCE")
    @Column(length = 2048)
    private String stateProvince;
    @JacksonXmlProperty(localName = "STREET")
    @Column(length = 2048)
    private String street;
    @JacksonXmlProperty(localName = "CITY")
    @Column(length = 2048)
    private String city;
    @JacksonXmlProperty(localName = "ZIP_CODE")
    @Column(length = 2048)
    private String zipCode;
}
