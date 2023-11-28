package com.cbo.riskmanagement.model.pep;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PepList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @CsvBindByName(column = "id")
    private String Id;
    @Column(length = 2048)
    @CsvBindByName(column = "nameeng")
    private String nameInEng;
    @CsvBindByName(column = "nameamh")
    @Column(length = 2048)
    private String nameInAmh;
    @Column(length = 2048)
    @CsvBindByName(column = "position")
    private String position;
    @Column(length = 2048)
    @CsvBindByName(column = "placeofassignment")
    private String placeOfAssignment;
    @Column(length = 2048)
    @CsvBindByName(column = "detail")
    private String details;
}
