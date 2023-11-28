package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@DiscriminatorValue(value = "Positions")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Positions   {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ElementCollection

    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty(localName = "Position")
    private List<String> position;
}
