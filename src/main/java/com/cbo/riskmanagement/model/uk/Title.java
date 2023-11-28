package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "Title")
    private String title;
}
