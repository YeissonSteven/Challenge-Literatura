package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") int birth_year,
        @JsonAlias("death_year") int death_year
) {
}
