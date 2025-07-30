package com.alura.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T convertirDatos(String respuesta, Class<T> clase) {
        try {
            return mapper.readValue(respuesta,clase);
        } catch (
                JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
