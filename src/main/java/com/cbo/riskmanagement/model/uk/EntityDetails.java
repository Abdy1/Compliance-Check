package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor

public class EntityDetails{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;
    @JacksonXmlProperty(localName = "Entity")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "uk_entity_id")
    private List<UkEntity> ukEntity;
}
