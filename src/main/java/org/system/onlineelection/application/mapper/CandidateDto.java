package org.system.onlineelection.application.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateDto {
    private Integer id;
    private String candidateName;
    private String candidateLastName;
    private String userName;
    private String job;
    private String namePoliticalParty;

}
