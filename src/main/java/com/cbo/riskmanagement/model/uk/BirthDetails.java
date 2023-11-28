package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BirthDetails {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @JacksonXmlProperty(localName = "Location")
    private List<Location> locationList;
}
