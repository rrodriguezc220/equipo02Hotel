package com.equipo02.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal que inicia la aplicación Spring Boot.
 */

@EnableFeignClients
@SpringBootApplication
public class Equipo02HotelApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     * @param args Los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(Equipo02HotelApplication.class, args);
    }

}