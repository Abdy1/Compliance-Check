package com.cbo.riskmanagement.model.us;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "address")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GptAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    @Column(name = "uid")
    private Long uid;
    @Column(name = "address_num")
    private int addressNum;
    @Column( name = "address",columnDefinition = "text")
    private String address;
    @Column( name = "city_province_postal_code",columnDefinition = "text")
    private String cityProvincePostalCode;
    @Column( name = "country", columnDefinition = "text")
    private String country;
    @Column(name = "add_remarks", columnDefinition = "text")
    private String addRemarks;


    @ManyToOne
    @JsonIgnoreProperties("sdnList")
    @JoinColumn(name = "sdn_list_uid")
    private GptSdnList  sdnList;


}
