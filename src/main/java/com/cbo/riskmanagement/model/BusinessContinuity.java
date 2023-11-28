package com.cbo.riskmanagement.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
public class BusinessContinuity {
    @Id
    @CsvBindByName(column = "delinquent_list_id")
    private Long  delinquent_list_id;


    @CsvBindByName(column = "rlog_create_date_time")
    @Column(columnDefinition="text")
    private String rlog_create_date_time;

    @CsvBindByName(column = "rlog_edit_date_time")
    @Column(columnDefinition="text")
    private String rlog_edit_date_time;
    @CsvBindByName(column = "customer_name")
    @Column(columnDefinition="text")
    private String customer_name;

    @CsvBindByName(column = "mother  name")
    @Column(columnDefinition="text")
    private String mother_name;


    @CsvBindByName(column = "nbe_reference")
    @Column( columnDefinition = "text")
    private String nbe_reference;
    @CsvBindByName(column = "account_number")
    @Column( columnDefinition = "text")
    private String account_number;
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

    //
    @Column(columnDefinition = "text")
    private Integer phone_number;

    @Column(columnDefinition = "text")
    private String kebele_id_number;




}
