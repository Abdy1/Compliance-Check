package com.cbo.riskmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDetails {
    private String firstName;
    private String lastName;
    private String role;
}
