package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnEntity {
    @Id
    @Column(name="entityId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "DATAID")
    private String dataId;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "VERSIONNUM")
    private String VersionNum;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "FIRST_NAME")
    private String firstName;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "SECOND_NAME")
    private String secondName;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "THIRD_NAME")
    private String thirdName;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "UN_LIST_TYPE")
    private String unListType;
    @Column(length = 2048)
    @JacksonXmlProperty(localName = "REFERENCE_NUMBER")
    private String referenceNumber;
    @JacksonXmlProperty(localName = "LISTED_ON")
    @Column(length = 2048)
    private String listedOn;
    @JacksonXmlProperty(localName = "COMMENTS1")
    @Column(length = 2048)
    private String comments1;
    @JacksonXmlProperty(localName = "LIST_TYPE")
    @Column(length = 2048)
    private String listType;
    @JacksonXmlProperty(localName = "LAST_DAY_UPDATED")
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private LastDayUpdated lastDayUpdated;
    @JacksonXmlProperty(localName = "ENTITY_ALIAS")
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private UnEntityAlias unEntityAlias;
    @JacksonXmlProperty(localName = "ENTITY_ADDRESS")
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private UnEntityAddress unEntityAddress;
    @JacksonXmlProperty(localName = "SORT_KEY")
    @Column(length = 2048)
    private String sortKey;
    @JacksonXmlProperty(localName = "SORT_KEY_LAST_MOD")
    @Column(length = 2048)
    private String sortKeyLastMod;
    @JacksonXmlProperty(localName = "NAME_ORIGINAL_SCRIPT")
    @Column(length = 2048)
    private String nameOriginalScript;
    @JacksonXmlProperty(localName = "SUBMITTED_ON")
    @Column(length = 2048)
    private String submittedOn;
}
