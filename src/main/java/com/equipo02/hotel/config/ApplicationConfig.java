
package com.equipo02.hotel.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración de la aplicación que define un bean para ModelMapper.
 */
@Configuration
public class ApplicationConfig {
    
    /**
     * Define un bean de ModelMapper para ser utilizado en la aplicación.
     * @return Una instancia de ModelMapper.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}