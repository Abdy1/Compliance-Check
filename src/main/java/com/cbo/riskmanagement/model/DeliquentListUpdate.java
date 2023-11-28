package com.cbo.riskmanagement.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DeliquentListUpdate {
    @Id
    @CsvBindByName(column = "delinquent_list_id")
    private Long  delinquent_list_id;
    @CsvBindByName(column = "rlog_create_date_time")
    @Column(columnDefinition="text")
    private String rlog_create_date_time;

    @CsvBindByName(column = "rlog_create_user_name")
    @Column(columnDefinition="text")
    private String rlog_create_user_name;

    @CsvBindByName(column = "rlog_edit_date_time")
    @Column(columnDefinition="text")
    private String rlog_edit_date_time;
    @CsvBindByName(column = "customer_name")
    @Column(columnDefinition="text")
    private String customer_name;

    @CsvBindByName(column = "nbe_reference")
    @Column( columnDefinition = "text")
    private String nbe_reference;
    @CsvBindByName(column = "TIN No./Account_number/")
    @Column( columnDefinition = "text")
    private String tin_Account;
    @CsvBindByName(column = "data_closed")
    @Column( columnDefinition = "text")
    private String data_closed;
    @CsvBindByName(column = "bank")
    @Column( columnDefinition = "text")
    private String bank;
    @CsvBindByName(column = "branch")
    @Column( columnDefinition = "text")
    private String branch;

    @CsvBindByName(column = "remark")
    @Column( columnDefinition = "text")
    private String remark;



}
