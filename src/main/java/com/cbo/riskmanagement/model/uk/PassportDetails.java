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
@AllArgsConstructor
@Entity
public class PassportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "Passport")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private List<Passport> passports;
}
