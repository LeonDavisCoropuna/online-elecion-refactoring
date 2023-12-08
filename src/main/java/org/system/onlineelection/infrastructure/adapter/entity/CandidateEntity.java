package org.system.onlineelection.infrastructure.adapter.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="candidato")
public class CandidateEntity extends ElectorEntity {

    private String job;

    @ManyToOne
    @JoinColumn(name = "id_partido")
    private PoliticalPartyEntityDto politicalParty;

}
