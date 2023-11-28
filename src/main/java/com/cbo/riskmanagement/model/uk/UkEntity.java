package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class UkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_of_entities_id")
    @JacksonXmlProperty(localName = "TypeOfEntities")
    private TypeOfEntities typeOfEntities;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_registration_numbers_id")
    @JacksonXmlProperty(localName = "BusinessRegistrationNumbers")
    private BusinessRegistrationNumbers businessRegistrationNumbers;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_companies_id")
    @JacksonXmlProperty(localName = "ParentCompanies")
    private ParentCompanies parentCompanies;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subsidiaries_id")
    @JacksonXmlProperty(localName = "Subsidiaries")
    private Subsidiaries subsidiaries;
}
