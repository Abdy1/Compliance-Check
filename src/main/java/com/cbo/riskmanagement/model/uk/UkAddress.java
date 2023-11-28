package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
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
public class UkAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AddressCountry;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AddressLine1;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AddressLine2;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AddressLine3;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AddressLine4;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AddressLine5;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AddressLine6;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AddressPostalCode;


}
