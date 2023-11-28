package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class SanctionsImposedIndicators{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String AssetFreeze;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String ArmsEmbargo;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String TargetedArmsEmbargo;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String CharteringOfShips;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String ClosureOfRepresentativeOffices;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String CrewServicingOfShipsAndAircraft;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String Deflag;
    @JacksonXmlProperty
    private String PreventionOfBusinessArrangements;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String ProhibitionOfPortEntry;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String TravelBan;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String PreventionOfCharteringOfShips;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String PreventionOfCharteringOfShipsAndAircraft;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String TechnicalAssistanceRelatedToAircraft;
    @Column(columnDefinition="text", length=10485760)
    @JacksonXmlProperty
    private String TrustServicesSanctions;


}
