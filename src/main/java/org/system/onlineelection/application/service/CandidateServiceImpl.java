package org.system.onlineelection.application.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.onlineelection.application.mapper.CandidateDto;
import org.system.onlineelection.application.mapper.EntityMapping;
import org.system.onlineelection.domain.model.Candidate;
import org.system.onlineelection.domain.port.output.CandidateServicePort;
import org.system.onlineelection.infrastructure.adapter.entity.CandidateEntity;
import org.system.onlineelection.infrastructure.repository.CandidateRepository;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class CandidateServiceImpl implements CandidateServicePort {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private final EntityMapping entityMapping;

    @Override
    public ArrayList<CandidateDto> getAllCandidates() {
        ArrayList<CandidateDto> candidates = new ArrayList<>();

        Iterable<CandidateEntity> candidateIterable = candidateRepository.findAll();

        // Iterar sobre los candidatos y obtener información adicional
        for (CandidateEntity candidateEntity : candidateIterable) {

            Candidate newCandidate = entityMapping.candidateMapping(candidateEntity);

            CandidateDto newCandidateDto = new CandidateDto();

            // Obtener datos personales del candidato
            newCandidateDto.setId( newCandidate.getId() );
            newCandidateDto.setCandidateName(newCandidate.getName());
            newCandidateDto.setCandidateLastName(newCandidate.getLastName());
            newCandidateDto.setUserName(newCandidate.getUserName());
            newCandidateDto.setJob(newCandidate.getJob());

            // Obtener datos del partido político
            newCandidateDto.setNamePoliticalParty(newCandidate.getPoliticalParty().getNamePoliticalParty());

            candidates.add(newCandidateDto);
        }

        return candidates;
    }

}
