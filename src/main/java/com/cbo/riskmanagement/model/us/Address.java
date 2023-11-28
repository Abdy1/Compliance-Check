//package com.cbo.riskmanagement.model.us;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//public class Address {
//
//    @jakarta.persistence.Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;
//    private static long lastId = 0;
//
//
//    private Long uid;
//
//    private int addressNum;
//    @Column( columnDefinition = "text")
//    private String address;
//    @Column( columnDefinition = "text")
//    private String cityProvincePostalCode;
//    @Column( columnDefinition = "text")
//    private String country;
//    @Column( columnDefinition = "text")
//    private String addRemarks;
//
//
//    @ManyToOne
//    @JoinColumn(name = "sdn_list_uid", referencedColumnName = "uid")
//    private SdnList  sdnList;
//
//    public Address() {
//        this.id = ++lastId;
//    }
//}
