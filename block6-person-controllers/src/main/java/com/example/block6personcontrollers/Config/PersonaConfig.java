package com.example.block6personcontrollers.Config;

import com.example.block6personcontrollers.Model.Persona;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonaConfig {

    @Bean(name = "bean1")
    public Persona crearPersonaBean1() {
        return new Persona("bean1");
    }

    @Bean(name = "bean2")
    public Persona crearPersonaBean2() {
        return new Persona("bean2");
    }

    @Bean(name = "bean3")
    public Persona crearPersonaBean3() {
        return new Persona("bean3");
    }
}

