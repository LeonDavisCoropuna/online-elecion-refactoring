package org.system.onlineelection.application.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.system.onlineelection.infrastructure.adapter.entity.ERole;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntityDto {

    private Integer id;

    private ERole nameRole;
}
