package com.cbo.riskmanagement.model.us;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gpt_sdn_list")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uid")
public class GptSdnList {

    @Id
    @Column(name = "uid")
    private Long uid;

    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "sdn_type", columnDefinition = "text")
    private String sdnType;

    @Column(name = "program", columnDefinition = "text")
    private String program;

    @Column(name = "title", columnDefinition = "text")
    private String title;
    @Column( name = "call_sign",columnDefinition = "text")
    private String callSign;
    @Column( name = "ves_type",columnDefinition = "text")
    private String vessType;
    @Column(name = "tonnage", columnDefinition = "text")
    private String tonnage;
    @Column(name = "grt", columnDefinition = "text")
    private String grt;
    @Column(name = "vess_flag", columnDefinition = "text")
    private String vessFlag;
    @Column( name = "vess_owner",columnDefinition = "text")
    private String vessOwner;
    @Column( name = "remarks",columnDefinition = "text")
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sdn_list_uid")
    private List<GptAka> akaList;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "sdn_list_uid")
    private List<GptAddress> addressList;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sdn_comments_uid", referencedColumnName = "id")
    private GptSdnCommentsEntity remarkExtended;



}
