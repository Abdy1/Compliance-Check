package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Regulation {
//    @Id
//    @GeneratedValue(strategy= GenerationType.UUID)
//    private String Id;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String regulationType;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String organisationType;
    @Column(length = 2048)
    @JacksonXmlProperty(isAttribute = true)
    private String publicationDate;
    @Column(length = 2048)
    @JacksonXmlProperty(isAttribute = true)
    private String entryIntoForceDate;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "regulationnumberTitle", length = 2048)
    private String numberTitle;
    @Column(length = 2048)
    @JacksonXmlProperty(isAttribute = true)
    private String programme;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "regulationLogicalId")
    private String logicalId;
    @JacksonXmlProperty
    @Column(length = 2048)
    private  String publicationUrl;
}
