package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Name{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String Name1;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String Name2;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String Name3;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String Name4;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String Name5;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String Name6;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String NameType;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AliasStrength;
}
