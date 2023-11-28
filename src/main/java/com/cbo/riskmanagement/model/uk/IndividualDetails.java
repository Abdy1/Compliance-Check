package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@NoArgsConstructor
@Entity
public class IndividualDetails {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "individual_details_id")
    @JacksonXmlProperty(localName = "Individual")
    private List<UkIndividual> individual;
}
