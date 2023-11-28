package com.cbo.riskmanagement.model.uk;

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
public class Names {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;
    @JacksonXmlProperty(localName = "Name")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "name_id")
    private List<Name> nameList;
}
