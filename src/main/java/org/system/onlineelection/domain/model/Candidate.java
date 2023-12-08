package org.system.onlineelection.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Candidate extends Elector{
    private String job;

    private PoliticalParty politicalParty;

}
