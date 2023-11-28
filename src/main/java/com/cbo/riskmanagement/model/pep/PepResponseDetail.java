package com.cbo.riskmanagement.model.pep;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PepResponseDetail {

    private String Id;
    private String nameInEng;
    private String nameInAmh;
    private String position;
    private String placeOfAssignment;
    private String details;
}
