package com.cbo.riskmanagement.model.uk;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class CurrentOwnerOperators {
    @jakarta.persistence.Id
    private Long Id;
    @ElementCollection
    private List<String> CurrentOwnerOperator;
}
