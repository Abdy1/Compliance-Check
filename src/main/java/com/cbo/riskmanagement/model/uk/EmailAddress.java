package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class EmailAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @JacksonXmlText
    private String value;
}
