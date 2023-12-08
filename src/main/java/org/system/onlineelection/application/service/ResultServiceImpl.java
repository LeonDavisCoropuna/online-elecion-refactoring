package org.system.onlineelection.application.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.onlineelection.application.mapper.EntityMapping;
import org.system.onlineelection.application.mapper.ResultDto;
import org.system.onlineelection.domain.model.Candidate;
import org.system.onlineelection.domain.model.PoliticalParty;
import org.system.onlineelection.domain.port.output.ResultServicePort;
import org.system.onlineelection.infrastructure.adapter.entity.CandidateEntity;
import org.system.onlineelection.infrastructure.adapter.entity.PoliticalPartyEntityDto;
import org.system.onlineelection.infrastructure.repository.CandidateRepository;
import org.system.onlineelection.infrastructure.repository.PoliticalPartyRepository;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Service
public class ResultServiceImpl implements ResultServicePort {

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private final EntityMapping entityMapping;

    @Override
    public ArrayList<ResultDto> getResult() {

        Iterable<PoliticalPartyEntityDto> resultEntities = politicalPartyRepository.findAll();

        ArrayList<ResultDto> electionResults = new ArrayList<>();

        for (PoliticalPartyEntityDto politicalPartyEntity : resultEntities) {

            // Obtener y asignar datos del partido político
            PoliticalParty politicalParty = entityMapping.politicalPartyMapping(politicalPartyEntity);

            // Obtener y asignar datos del candidato
            CandidateEntity candidateEntity = candidateRepository.findByPoliticalParty_Id( politicalParty.getId() ).orElse(null);
            Candidate candidate = new Candidate();
            if (candidateEntity != null) {
                candidate = entityMapping.candidateMapping(candidateEntity);
            }


            ResultDto newResultDto = new ResultDto();

            newResultDto.setIdCandidate( candidate.getId() );
            newResultDto.setNameCandidate( candidate.getName() );
            newResultDto.setLastNameCandidate( candidate.getLastName() );
            newResultDto.setJobCandidate( candidate.getJob() );

            newResultDto.setIdPoliticalParty( politicalParty.getId() );
            newResultDto.setNamePoliticalParty( politicalParty.getNamePoliticalParty() );
            newResultDto.setNumVotes( politicalParty.getNumVotes() );


            // Agregar el nuevo resultado a la lista
            electionResults.add(newResultDto);
        }
        return electionResults;
    }

}