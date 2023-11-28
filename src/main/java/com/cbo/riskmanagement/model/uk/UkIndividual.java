package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
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
public class UkIndividual {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "DOBs")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dobs_id")
    private DOBs doBs;
    @JacksonXmlProperty(localName = "Nationalities")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nationalities_id")
    private UkNationalities Nationalities;
    @JacksonXmlProperty(localName = "Positions")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "positions_id")
    private Positions positions;
    @JacksonXmlProperty(localName = "BirthDetails")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "birth_details_id")
    private BirthDetails birthDetails;
    @JacksonXmlProperty(localName = "PassportDetails")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_details_id")
    private PassportDetails passportDetails;
    @JacksonXmlProperty(localName = "NationalIdentifierDetails")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "national_idenitifier_details_id")
    private NationalIdentifierDetails nationalIdentifierDetails;
    @JacksonXmlProperty(localName = "Genders")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genders_id")
    private Genders genders;
}
