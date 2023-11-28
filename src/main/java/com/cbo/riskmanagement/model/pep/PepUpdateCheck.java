package com.cbo.riskmanagement.model.pep;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PepUpdateCheck {
    @Id
    private Long  id;

    @Column
    private int updateCheck;
}
