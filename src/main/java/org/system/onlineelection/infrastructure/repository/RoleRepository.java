package org.system.onlineelection.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.system.onlineelection.infrastructure.adapter.entity.RoleEntity;


public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

}
