//package com.cbo.riskmanagement.model.us;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "sdn_list")
//public class SdnList {
//
//    @Id
//    @Column
//    private Long uid;
//
//    @Column( columnDefinition = "text")
//    private String name;
//    @Column( columnDefinition = "text")
//    private String sdnType;
//
//    @Column( columnDefinition = "text")
//    private String program;
//
//    @Column( columnDefinition = "text")
//    private String title;
//    @Column( columnDefinition = "text")
//    private String callSign;
//    @Column( columnDefinition = "text")
//    private String vessType;
//    @Column( columnDefinition = "text")
//    private String tonnage;
//    @Column( columnDefinition = "text")
//    private String grt;
//    @Column( columnDefinition = "text")
//    private String vessFlag;
//    @Column( columnDefinition = "text")
//    private String vessOwner;
//    @Column( columnDefinition = "text")
//    private String remarks;
//
//    @OneToMany(mappedBy = "sdnList",cascade = CascadeType.ALL)
//    private List<Aka> akaList;
//
//    @OneToMany(mappedBy = "sdnList", cascade = CascadeType.ALL)
//    private List<Address> addressList;
//
//    @OneToOne(mappedBy = "sdnList", cascade = CascadeType.ALL)
//    private SdnCommentsEntity remarkExtended;
//
//
//
//}
