package com.example.block5profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("INT")
public class Class implements Interface{

    @Override
    public void myMethod() {
        System.out.println("Ejecutando clase 1 con perfil INT");
    }
}
