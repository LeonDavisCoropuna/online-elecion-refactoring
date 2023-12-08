package org.system.onlineelection.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastName;

    @NotNull
    private String userName;
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "personId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<RoleEntity> roles = new HashSet<>();

    public PersonEntity(String username, String password) {
        this.userName = username;
        this.password = password;
    }


}
