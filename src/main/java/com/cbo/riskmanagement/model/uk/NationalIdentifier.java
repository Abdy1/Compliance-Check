package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NationalIdentifier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String NationalIdentifierNumber;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String NationalIdentifierAdditionalInformation;
}
