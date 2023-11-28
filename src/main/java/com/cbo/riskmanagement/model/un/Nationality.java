package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Nationality {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  Long Id;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "VALUE")
    private String value;
}
