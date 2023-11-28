package com.cbo.riskmanagement.model.us;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OfacSanction {
    @Id
    @CsvBindByName(column = "id")
    private String Id;
    @CsvBindByName(column = "schema")
    @Column(columnDefinition="text")
    private String schema;
    @CsvBindByName(column = "name")
    @Column(columnDefinition="text")
    private String name;
    @CsvBindByName(column = "aliases")
    @Column( columnDefinition = "text")
    private String aliases;
    @CsvBindByName(column = "birth_date")
    @Column( columnDefinition = "text")
    private String birthDate;
    @CsvBindByName(column = "countries")
    @Column( columnDefinition = "text")
    private String countries;
    @CsvBindByName(column = "addresses")
    @Column( columnDefinition = "text")
    private String addresses;
    @CsvBindByName(column = "identifiers")
    @Column( columnDefinition = "text")
    private String identifiers;
    @CsvBindByName(column = "sanctions")
    @Column( columnDefinition = "text")
    private String sanctions;
    @CsvBindByName(column = "phones")
    @Column( columnDefinition = "text")
    private String phones;
    @CsvBindByName(column = "emails")
    @Column( columnDefinition = "text")
    private String emails;
    @CsvBindByName(column = "dataset")
    @Column( columnDefinition = "text")
    private String dataSet;
    @CsvBindByName(column = "first_seen")
    @Column( columnDefinition = "text")
    private String firstSeen;
    @CsvBindByName(column = "last_seen")
    @Column( columnDefinition = "text")
    private String lastSeen;
}
