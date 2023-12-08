package org.system.onlineelection.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vote {

    private Integer id;
    private Elector elector;
    private PoliticalParty politicalParty;
    private Date date;

}
