package org.system.onlineelection.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "partido_politico")
public class PoliticalPartyEntityDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String namePoliticalParty;

    @OneToMany(mappedBy = "politicalParty")
    private List<CandidateEntity> candidates;

    private Integer numVotes = 0;
}
