package org.system.onlineelection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.system.onlineelection.application.mapper.RoleEntityDto;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person {

    private Integer id;
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private Set<RoleEntityDto> roles = new HashSet<>();

    public Person(String username, String password) {
        this.userName = username;
        this.password = password;
    }

}
