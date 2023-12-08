package org.system.onlineelection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.system.onlineelection.infrastructure.adapter.entity.CandidateEntity;

import java.util.List;

//model domain
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PoliticalParty {

    private Integer id;
    private String namePoliticalParty;
    private Integer numVotes;
}
