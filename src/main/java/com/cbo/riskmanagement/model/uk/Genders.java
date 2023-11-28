package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Genders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty(localName = "Gender")
    private String gender;
}
