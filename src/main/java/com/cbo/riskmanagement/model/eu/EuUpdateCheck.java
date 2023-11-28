package com.cbo.riskmanagement.model.uk;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EuUpdateCheck {
    @Id
    private Long  id;

    @Column
    private int updateCheck;
}
