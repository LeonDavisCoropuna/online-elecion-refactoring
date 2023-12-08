package org.system.onlineelection.infrastructure.adapter.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private Integer id;
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private Set<String> roles;

}