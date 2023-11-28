package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@ToString

@Entity
public class NameAlias {
    @Id
  @GeneratedValue(strategy = GenerationType.UUID)
    String Id;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String firstName;
    @JacksonXmlProperty(localName = "remark")
    @Column(name = "remarkNameAlias",length = 2048)
    private String remark;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String middleName;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String lastName;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String wholeName;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String function;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String gender;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String title;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String nameLanguage;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048)
    private String strong;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "regulationLanguageNameAlias",length = 2048)
    private String regulationLanguage;
    @JacksonXmlProperty(isAttribute = true)
    @Column(name = "nameAliasLogicalId", length = 2048)
    private String logicalId;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "numberTitle", column = @Column(name = "numbeTitleNameAlias")),
            @AttributeOverride( name = "publicationDate", column = @Column(name = "publicationDateInNameAlias")),
            @AttributeOverride(name = "publicationUrl", column = @Column(name = "publicationUrlNameAlias")),
            @AttributeOverride(name = "regulationType", column = @Column(name = "regulationTypeNameAlias"))
    })
    private RegulationSummary regulationSummary;

}
