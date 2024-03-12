package com.webdynamos.fincas;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.modelmapper.convention.MatchingStrategies; 

@Configuration // Add this annotation
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); 
        return new ModelMapper();
    }
}
