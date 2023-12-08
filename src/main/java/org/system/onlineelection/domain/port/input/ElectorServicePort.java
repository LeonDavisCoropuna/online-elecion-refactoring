package org.system.onlineelection.domain.port.input;



import org.system.onlineelection.application.mapper.VoteDto;
import org.system.onlineelection.domain.model.PoliticalParty;

import java.util.ArrayList;

public interface ElectorServicePort {
    Boolean saveVote(VoteDto vote);
    PoliticalParty getResultVoteOfPoliticalParty(Integer idPoliticalParty);
    ArrayList<PoliticalParty> getPoliticalParty();

}
