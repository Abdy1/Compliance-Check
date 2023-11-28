package com.cbo.riskmanagement.model.nbeblacklist;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class NbeBacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @CsvBindByName(column = "S/N")
    private String Id;
    @CsvBindByName(column = "NAME")
    private String name;
    @CsvBindByName(column = "OFFENCE")
    private String offence;


}
