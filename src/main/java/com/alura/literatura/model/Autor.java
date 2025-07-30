package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int birthYear;
    private int deathYear;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libros> libros = new ArrayList<>();

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }

    public Autor() {
    }

    public void mostrarAutor() {
        System.out.println("**************************");
        System.out.println("Autor: " + name);
        System.out.println("ano de nacimiento: " + birthYear);
        System.out.println("ano de muerte: " + deathYear);
        System.out.println("Libros:");

        if (libros.isEmpty()) {
            System.out.println("  (No hay libros registrados)");
        } else {
            for (Libros libro : libros) {
                System.out.println("  - " + libro.getTitulo());
            }
        }
        System.out.println("**************************");
        System.out.println("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birth_year) {
        this.birthYear = birth_year;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int death_year) {
        this.deathYear = death_year;
    }

    @Override
    public String toString() {
        return name;
    }
}
