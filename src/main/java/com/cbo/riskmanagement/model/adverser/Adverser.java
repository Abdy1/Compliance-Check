package com.cbo.riskmanagement.model.adverser;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Adverser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CsvBindByName(column = "S/N")
    private Long Id;
    @CsvBindByName(column = "Name")
    String name;
    @CsvBindByName(column = "Position")
    String position;
}
