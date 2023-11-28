package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@JacksonXmlRootElement(localName = "Designations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Designations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long super_designation_Id;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String DateGenerated;
    @JacksonXmlProperty(localName = "Designation")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "designations_id")
    private List<UkDesignation> ukDesignation;

}
