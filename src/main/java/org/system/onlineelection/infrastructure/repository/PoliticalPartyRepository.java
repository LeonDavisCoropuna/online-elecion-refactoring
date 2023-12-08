package org.system.onlineelection.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.system.onlineelection.infrastructure.adapter.entity.PoliticalPartyEntityDto;


@Repository
public interface PoliticalPartyRepository extends CrudRepository<PoliticalPartyEntityDto, Integer> {

}
