package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Individuals {
    @jakarta.persistence.Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "INDIVIDUAL")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<UnIndividual> unIndividualList;
}
