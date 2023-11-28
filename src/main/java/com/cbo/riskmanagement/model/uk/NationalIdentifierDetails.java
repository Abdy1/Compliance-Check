package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "NationalIdentifierDetails")
public class NationalIdentifierDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty(localName = "NationalIdentifier")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "National_Indetifier_Id")
    private List<NationalIdentifier> nationalIdentifier;
}
