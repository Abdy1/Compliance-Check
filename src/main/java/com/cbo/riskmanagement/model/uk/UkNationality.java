package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UkNationality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "Nationality")
    private String value;
}
