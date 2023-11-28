package com.cbo.riskmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class OfacUpdateCheck {
    @Id
    private Long  id;

    @Column
    private int updateCheck;
}
