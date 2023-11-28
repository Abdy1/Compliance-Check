package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class UkAddresses {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JacksonXmlProperty(localName = "Address")
    private List<UkAddress> ukAddressList;
}
