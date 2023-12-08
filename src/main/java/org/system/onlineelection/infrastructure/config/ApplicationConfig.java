package org.system.onlineelection.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.system.onlineelection.application.mapper.EntityMapping;
import org.system.onlineelection.infrastructure.adapter.jwt.JwtUtils;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    @Bean
    public JwtUtils jwtUtils(){return new JwtUtils();}
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public EntityMapping entityMapping(){
        return new EntityMapping();
    }

}