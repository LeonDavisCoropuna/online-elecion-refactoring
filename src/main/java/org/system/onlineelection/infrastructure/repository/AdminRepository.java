package org.system.onlineelection.infrastructure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.system.onlineelection.infrastructure.adapter.entity.AdminEntity;


@Repository
public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
}
