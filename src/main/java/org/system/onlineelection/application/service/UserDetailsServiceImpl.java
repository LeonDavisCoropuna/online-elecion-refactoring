package org.system.onlineelection.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.system.onlineelection.application.service.UserDetailsImpl;
import org.system.onlineelection.domain.model.Person;
import org.system.onlineelection.application.mapper.RoleEntityDto;
import org.system.onlineelection.infrastructure.adapter.entity.RoleEntity;
import org.system.onlineelection.infrastructure.adapter.entity.PersonEntity;
import org.system.onlineelection.infrastructure.repository.PersonRepository;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PersonEntity user = personRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        Set<RoleEntityDto> roleEntityDtos = user.getRoles().stream()
                .map(RoleEntity::toDto)
                .collect(Collectors.toSet());

        Person userPerson = Person.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .lastName(user.getLastName())
                .name(user.getName())
                .password(user.getPassword())
                .roles(roleEntityDtos)
                .build();
        return UserDetailsImpl.build(userPerson);
    }

}