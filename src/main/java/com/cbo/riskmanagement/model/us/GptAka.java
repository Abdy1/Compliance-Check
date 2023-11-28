package com.cbo.riskmanagement.model.us;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "gpt_aka")
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GptAka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "uid")
    private Long uid;

    @Column(name = "alt_num")
    private int altNum;

    @Column(name = "alt_type", columnDefinition = "text")
    private String altType;
    @Column( name = "alt_name",columnDefinition = "text")
    private String altName;
    @Column( name = "alt_remarks",columnDefinition = "text")
    private String altRemarks;

    @ManyToOne
    @JsonIgnoreProperties("sdnList")
    @JoinColumn(name = "sdn_list_uid")
    private GptSdnList sdnList;

}
