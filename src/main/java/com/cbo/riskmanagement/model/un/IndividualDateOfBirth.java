package com.cbo.riskmanagement.model.un;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class IndividualDateOfBirth {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @JacksonXmlProperty(localName = "TYPE_OF_DATE")
    @Column(length = 2048)
    private String typeOfDate;
    @JacksonXmlProperty(localName = "DATE")
    @Column(length = 2048)
    private String date;
    @JacksonXmlProperty(localName = "YEAR")
    @Column(length = 2048)
    private String year;
    @JacksonXmlProperty(localName = "FROM_YEAR")
    @Column(length = 2048)
    private String fromYear;
    @JacksonXmlProperty(localName = "TO_YEAR")
    @Column(length = 2048)
    private String toYear;
    @JacksonXmlProperty(localName = "NOTE")
    @Column(length = 2048)
    private String note;
}
