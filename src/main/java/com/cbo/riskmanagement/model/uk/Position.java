package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "Position")
    @Column(length = 2048)
    private String value;
}
