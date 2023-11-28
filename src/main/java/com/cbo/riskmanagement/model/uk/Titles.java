package com.cbo.riskmanagement.model.uk;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Titles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ElementCollection
    @JacksonXmlProperty(localName = "Title")
    private List<String> Title;
}
