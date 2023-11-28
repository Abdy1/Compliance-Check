package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LastDayUpdated {
    @jakarta.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    @JacksonXmlProperty(localName = "VALUE")
    @Column(length = 2048)
    private String value;
}
