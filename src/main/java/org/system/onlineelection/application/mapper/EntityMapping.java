package org.system.onlineelection.application.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.system.onlineelection.domain.model.*;
import org.system.onlineelection.infrastructure.adapter.entity.*;

public class EntityMapping {

    @Autowired
    private ModelMapper modelMapper;

    public Candidate candidateMapping(CandidateEntity candidateEntity)
    {
        Candidate candidate = modelMapper.map(candidateEntity, Candidate.class);
        return candidate;
    }

    public Admin adminMapping (AdminEntity adminEntity){
        return modelMapper.map(adminEntity, Admin.class);
    }

    public Elector electorMapping (ElectorEntity electorEntity){
        Elector elector = modelMapper.map(electorEntity, Elector.class);
        return elector;
    }
    public Person personMapping (PersonEntity personEntity){
        return modelMapper.map(personEntity, Person.class);

    }
    public PoliticalParty politicalPartyMapping  (PoliticalPartyEntityDto politicalPartyEntity) {
        return modelMapper.map(politicalPartyEntity, PoliticalParty.class);
    }

    public Vote voteMapping (VoteEntity voteEntity){
        return modelMapper.map(voteEntity, Vote.class);
    }
}
