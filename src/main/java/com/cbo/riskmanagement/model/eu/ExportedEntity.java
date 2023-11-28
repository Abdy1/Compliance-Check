package com.cbo.riskmanagement.model.eu;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;
@Data
@JacksonXmlRootElement(localName = "export")
public class ExportedEntity {
    @JacksonXmlProperty(isAttribute = true)
    private String generationDate;
    @JacksonXmlProperty(isAttribute = true)
    private String globalFileId;
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<SanctionEntity> sanctionEntity;
}
