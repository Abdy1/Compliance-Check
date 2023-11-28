package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@ToString
@Entity
@Table(name = "sanctionentity")

public class SanctionEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String Id;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String designationDetails;
    @JacksonXmlProperty
    @Embedded
    private Address address;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String unitedNationId;
    @JacksonXmlProperty(localName = "logicalId",isAttribute = true)
    @Column(name = "sanctionEntintyLogicalId",length = 2048)
    private String logicalId;
    @JacksonXmlProperty(localName = "remark")
    @Column(length = 2048, name = "sanctionEntityRemark")
    private String remark;
    @Embedded
    @JacksonXmlProperty
    private Regulation regulation;
    @Embedded
    @JacksonXmlProperty
    private SubjectType subjectType;

    @JacksonXmlProperty


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sanction_id", nullable = false)
    private List<NameAlias> nameAlias;
    @Embedded
    @JacksonXmlProperty(localName = "citizenship")
    private Citizenship citizenship;

    @JacksonXmlProperty(localName = "birthdate")
    @Embedded
    private BirthDate birthDate;
    @JacksonXmlProperty
    @Embedded
    private Identification identification;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String designationDate;
}