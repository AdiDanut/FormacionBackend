package com.example.block5profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class Clase2 implements Interface{
    @Override
    public void myMethod() {
        System.out.println("Ejecutando clase 2 con perfil Local");
    }
}
