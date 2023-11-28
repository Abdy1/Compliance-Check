package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class IMONumbers {
    @Id
    private Long Id;
    @JacksonXmlProperty
    @ElementCollection
    private List<String> IMONumber;
}

