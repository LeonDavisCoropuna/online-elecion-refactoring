package org.system.onlineelection.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.system.onlineelection.infrastructure.adapter.entity.CandidateEntity;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Integer>  {

    Optional<CandidateEntity> findByPoliticalParty_Id(Integer politicalPartyId);
}
