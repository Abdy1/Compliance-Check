package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class ParentCompanies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "ParentCompany")
    @ElementCollection
    private List<String> parentCompanyList;
}
