package org.system.onlineelection.domain.port.output;


import org.system.onlineelection.application.mapper.ResultDto;

import java.util.ArrayList;

public interface ResultServicePort {
    ArrayList<ResultDto> getResult();

}
