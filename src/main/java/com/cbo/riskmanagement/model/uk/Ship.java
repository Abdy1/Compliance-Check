package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ship {
    @Id
    private Long Id;
    @JacksonXmlProperty(localName = "IMONumbers")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "imo_numbers_id")
    private IMONumbers imoNumbers;
    @JacksonXmlProperty(localName = "CurrentOwnerOperators")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currentOwnerOperators_id")
    private CurrentOwnerOperators currentOwnerOperators;


}
