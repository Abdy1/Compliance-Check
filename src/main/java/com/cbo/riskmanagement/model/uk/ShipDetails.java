package com.cbo.riskmanagement.model.uk;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ShipDetails {
    @Id
    private Long Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ships_id")
    private Ship ship;
}
