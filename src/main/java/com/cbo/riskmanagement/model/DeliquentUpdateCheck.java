package com.cbo.riskmanagement.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DeliquentUpdateCheck {
    @Id
    private Long  id;

    @Column
    private int updateCheck;
}
