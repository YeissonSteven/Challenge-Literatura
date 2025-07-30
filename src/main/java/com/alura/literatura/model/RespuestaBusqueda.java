package com.alura.literatura.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespuestaBusqueda(
        List<DatosLibros> results
) {}

