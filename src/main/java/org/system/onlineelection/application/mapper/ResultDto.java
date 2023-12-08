package org.system.onlineelection.application.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultDto {
    private Integer idCandidate;
    private String nameCandidate;
    private String lastNameCandidate;
    private String jobCandidate;

    private Integer idPoliticalParty;
    private String namePoliticalParty;
    private Integer numVotes;
}
