package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@JacksonXmlRootElement(localName = "CONSOLIDATED_LIST")
@Entity
public class Consolidated {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @JacksonXmlElementWrapper(localName = "INDIVIDUALS")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Individuals individuals;
    @JacksonXmlElementWrapper(localName = "ENTITIES")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<UnEntity> entities;
   }
