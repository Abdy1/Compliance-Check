package com.cbo.riskmanagement.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
public class UsersAddedThisWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CsvBindByName(column = "Customer Name")
    private String customerName;
}
