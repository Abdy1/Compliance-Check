package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class UkDesignation {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  Id;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String LastUpdated;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String DateDesignated;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String UniqueID;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String OFSIGroupID;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String UNReferenceNumber;
    @JacksonXmlProperty(localName = "Names")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "names_id")
    private Names names;
    @JacksonXmlProperty(localName = "NonLatinNames")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "non_lation_names_id")
    private NonLatinNames nonLatinNames;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String RegimeName;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String IndividualEntityShip;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String DesignationSource;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String SanctionsImposed;
    @JacksonXmlProperty(localName = "SanctionsImposedIndicators")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sanctionsImposedIndicators_Id")
    private SanctionsImposedIndicators sanctionsImposedIndicators;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String OtherInformation;
    @JacksonXmlProperty(localName = "IndividualDetails")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "individual_details_id")
    private IndividualDetails individualDetails;
    @JacksonXmlProperty(localName = "Titles")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title_id")
    private Titles titles;
    @JacksonXmlProperty
    @Column(columnDefinition="text", length=10485760)
    private String UKStatementofReasons;
    @JacksonXmlProperty
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addresses_id")
    private UkAddresses Addresses;
    @JacksonXmlProperty(localName = "PhoneNumbers")
    @JacksonXmlElementWrapper
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_numbers_id")
    private PhoneNumbers phoneNumbers;
    @JacksonXmlProperty(localName = "EmailAddresses")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email_addresses_id")
    private EmailAddresses emailAddresses;
    @JacksonXmlProperty(localName = "EntityDetails")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_details_id")
    private EntityDetails entityDetails;
    @JacksonXmlProperty(localName = "Websites")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "websites_id")
    private Websites websites;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ship_details_id")
     private ShipDetails shipDetails;
}
