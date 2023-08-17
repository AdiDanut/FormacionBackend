package com.example.block7crudvalidation.Factory;


import com.example.block7crudvalidation.Exceptions.CustomError;

import java.util.Date;

public class CustomErrorFactory {

    static CustomError customError = new CustomError();

    public static CustomError createNotFound(String message, Long id) {
        customError.setHttpCode(404);
        customError.setTimestamp(new Date());
        customError.setMensaje(message + " con id: " + id + "no existe en la base de datos");
        return customError;
    }

    public static CustomError createNotFoundByUsuario(String usuario) {
        customError.setHttpCode(404);
        customError.setTimestamp(new Date());
        customError.setMensaje("Persona no encontrada con usuario: " + usuario);
        return customError;
    }

    public static CustomError createNullUser(){
        customError.setTimestamp(new Date());
        customError.setMensaje("Usuario no puede ser nulo");
        customError.setHttpCode(422);
        return customError;
    }

    public static CustomError createUserLength(){
        customError.setTimestamp(new Date());
        customError.setMensaje("Longitud de usuario no puede ser superior a 10 caracteres");
        customError.setHttpCode(422);
        return customError;
    }
}