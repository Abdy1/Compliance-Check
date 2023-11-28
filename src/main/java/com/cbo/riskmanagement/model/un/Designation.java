package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Designation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "VALUE")
    @Column(length = 2048)
    private String value;
}
