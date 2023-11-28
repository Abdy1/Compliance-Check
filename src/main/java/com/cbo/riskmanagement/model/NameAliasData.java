package com.cbo.riskmanagement.model;

import com.cbo.riskmanagement.model.eu.NameAlias;
import lombok.Data;

import java.util.List;

@Data
public class NameAliasData {
    private List<NameAlias> nameAliasList;
    private String sanctionId;
}
