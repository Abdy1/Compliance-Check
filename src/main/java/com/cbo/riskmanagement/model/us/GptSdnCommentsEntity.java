package com.cbo.riskmanagement.model.us;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gpt_sdn_comments_entity")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GptSdnCommentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "uid")
    private Long uid;


    @Column(name = "remarks_extended", columnDefinition = "text")
    private String remarkExtended;

    @OneToOne
    @JsonIgnoreProperties("sdnList")
    @JoinColumn(name = "sdn_list_uid", referencedColumnName = "uid")
    private GptSdnList sdnList;
    public GptSdnCommentsEntity(Long uid, Long id, String remarkExtended) {
        this.uid = uid;
        this.id = id;
        this.remarkExtended = remarkExtended;
    }


}