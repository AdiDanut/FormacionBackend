package com.example.block7crudvalidation.exceptions.factory;


import com.example.block7crudvalidation.exceptions.CustomError;

import java.util.Date;

public class CustomErrorFactory {

    private static final CustomError customError = new CustomError();

    private CustomErrorFactory() {

    }

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

    public static CustomError createUserProfesor(){
        customError.setTimestamp(new Date());
        customError.setHttpCode(422);
        customError.setMensaje("El usuario no puede ser profesor y alumno al mismo tiempo");
        return customError;
    }

}