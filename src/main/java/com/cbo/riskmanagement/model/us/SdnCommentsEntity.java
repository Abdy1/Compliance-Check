//package com.cbo.riskmanagement.model.us;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class SdnCommentsEntity {
//
//    @jakarta.persistence.Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private static long lastId = 0;
//
//    private Long uid;
//
//    @Column( columnDefinition = "text")
//    private String remarkExtended;
//
//    @OneToOne
//    @JoinColumn(name = "sdn_list_uid", referencedColumnName = "uid")
//    private SdnList sdnList;
//
//    public SdnCommentsEntity() {
//        this.id = ++lastId;
//    }
//
//}