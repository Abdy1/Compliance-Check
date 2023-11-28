package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity

@NoArgsConstructor
@AllArgsConstructor
public class DOBs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ElementCollection
    @JacksonXmlProperty
    private List<String> DOB;
}
