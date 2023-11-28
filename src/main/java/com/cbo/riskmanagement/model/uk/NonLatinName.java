package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NonLatinName {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(columnDefinition = "text", length = 10485760)
    @JacksonXmlProperty
    private String NameNonLatinScript;
    @Column(columnDefinition = "text", length = 10485760)
    @JacksonXmlProperty
    private String NonLatinScriptType;
    @JacksonXmlProperty
    @Column(columnDefinition = "text", length = 10485760)
    private String NonLatinScriptLanguage;
}

