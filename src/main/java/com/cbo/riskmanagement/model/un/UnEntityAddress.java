package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnEntityAddress {
    @jakarta.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "CITY")
    private String city;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "COUNTRY")
    private String country;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "NOTE")
    private String note;
    @JacksonXmlProperty(localName = "STATE_PROVINCE")
    @Column(length = 2048)
    private String stateProvince;
    @JacksonXmlProperty(localName = "STREET")
    @Column(length = 2048)
    private String street;
    @JacksonXmlProperty(localName = "ZIP_CODE")
    @Column(length = 2048)
    private String zipCode;
}
