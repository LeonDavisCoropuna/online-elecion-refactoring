package org.system.onlineelection.application.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.onlineelection.application.mapper.EntityMapping;
import org.system.onlineelection.application.mapper.VoteDto;
import org.system.onlineelection.domain.model.PoliticalParty;
import org.system.onlineelection.domain.port.input.ElectorServicePort;
import org.system.onlineelection.infrastructure.adapter.entity.ElectorEntity;
import org.system.onlineelection.infrastructure.adapter.entity.PoliticalPartyEntityDto;
import org.system.onlineelection.infrastructure.adapter.entity.VoteEntity;
import org.system.onlineelection.infrastructure.repository.ElectorRepository;
import org.system.onlineelection.infrastructure.repository.PoliticalPartyRepository;
import org.system.onlineelection.infrastructure.repository.VoteRepository;

import java.util.ArrayList;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ElectorServiceImpl implements ElectorServicePort {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ElectorRepository electorRepository;
    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private final EntityMapping entityMapping;

    @Override
    public Boolean saveVote(VoteDto vote) {

        //check this
        if (!voteRepository.existsById(vote.getIdElector())) {
            VoteEntity voteEntity = new VoteEntity();

            ElectorEntity electorEntity = electorRepository.findById(vote.getIdElector()).get();
            PoliticalPartyEntityDto politicalPartyEntityDto = politicalPartyRepository.findById(vote.getIdPoliticalParty()).get();

            voteEntity.setElector(electorEntity);
            voteEntity.setPoliticalParty(politicalPartyEntityDto);

            voteRepository.save(voteEntity);

            // Actualizar votos : Political Party

            PoliticalPartyEntityDto politicalPartyEntity = politicalPartyRepository.findById(vote.getIdPoliticalParty()).get();
            politicalPartyEntity.setNumVotes(politicalPartyEntity.getNumVotes() + 1);

            politicalPartyRepository.save(politicalPartyEntity);

            return true;
        }
        return false;

    }

    @Override
    public PoliticalParty getResultVoteOfPoliticalParty(Integer idPoliticalParty) {

        // double find
        Optional<PoliticalPartyEntityDto> data = politicalPartyRepository.findById(idPoliticalParty);

        PoliticalParty politicalParty = new PoliticalParty();

        if (data.isPresent()) {
            politicalParty = entityMapping.politicalPartyMapping(data.get());
            return politicalParty;
        }

        // Primer voto de un partido politico
        PoliticalPartyEntityDto politicalPartyEntityDto = politicalPartyRepository.findById(idPoliticalParty).get();
        politicalParty = entityMapping.politicalPartyMapping(politicalPartyEntityDto);
        return politicalParty;
    }

    @Override
    public ArrayList<PoliticalParty> getPoliticalParty() {
        Iterable<PoliticalPartyEntityDto> politicalPartiesIterable = politicalPartyRepository.findAll();

        ArrayList<PoliticalParty> politicalPartiesList = new ArrayList<>();

        for (PoliticalPartyEntityDto politicalPartyEntityDto : politicalPartiesIterable) {
            PoliticalParty politicalParty = entityMapping.politicalPartyMapping(politicalPartyEntityDto);
            politicalPartiesList.add(politicalParty);
        }

        return politicalPartiesList;
    }
}
