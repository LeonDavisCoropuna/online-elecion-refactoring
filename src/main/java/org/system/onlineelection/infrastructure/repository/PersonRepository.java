package org.system.onlineelection.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.system.onlineelection.infrastructure.adapter.entity.PersonEntity;


import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

    Optional<PersonEntity> findByUserName(String username);
}
