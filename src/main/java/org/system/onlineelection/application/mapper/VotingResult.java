package org.system.onlineelection.application.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VotingResult {
    private Integer id;
    private String namePoliticalParty;
    private String president;
    private Integer numVotes;
}
