package org.system.onlineelection.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="electores")
@Inheritance(strategy = InheritanceType.JOINED)
public class ElectorEntity extends PersonEntity {
    private String email;

    @OneToOne(mappedBy = "elector")
    private VoteEntity votes;

}
