//package com.cbo.riskmanagement.model.us;
//
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//public class Aka {
//
//    @jakarta.persistence.Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private static long lastId = 0;
//
//    private Long uid;
//
//    private int altNum;
//    @Column( columnDefinition = "text")
//    private String altType;
//    @Column( columnDefinition = "text")
//    private String altName;
//    @Column( columnDefinition = "text")
//    private String altRemarks;
//
//    @ManyToOne
//    @JoinColumn(name = "sdn_list_uid", referencedColumnName = "uid")
//    private SdnList sdnList;
//
//    public Aka() {
//        this.id = ++lastId;
//    }
//}
