package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
public class RegulationSummary {
    @JacksonXmlProperty(isAttribute = true)
    private String regulationType;
    @JacksonXmlProperty(localName = "publicationDate",isAttribute = true)
    @Column(length = 2048,name = "regulationTypepublicationDate")
    private String publicationDate;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048,name = "regulationTypenumberTitle")
    private String numberTitle;
    @JacksonXmlProperty(isAttribute = true)
    @Column(length = 2048, name = "regulationTypepublicationUrl")
    private String publicationUrl;

}
