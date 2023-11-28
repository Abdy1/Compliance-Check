package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class SubjectType {
//    @Id
//    @GeneratedValue(strategy= GenerationType.UUID)
//    private String Id;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String code;
    @Column(length = 2048)
    @JacksonXmlProperty(isAttribute = true)
    private String classificationCode;
}
