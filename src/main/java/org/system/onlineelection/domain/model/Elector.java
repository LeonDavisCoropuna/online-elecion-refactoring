package org.system.onlineelection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Elector extends Person{
    private String email;

    private Vote votes;

}
