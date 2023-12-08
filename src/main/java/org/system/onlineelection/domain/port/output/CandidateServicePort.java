package org.system.onlineelection.domain.port.output;



import org.system.onlineelection.application.mapper.CandidateDto;

import java.util.ArrayList;

public interface CandidateServicePort {
    ArrayList<CandidateDto> getAllCandidates();
}
