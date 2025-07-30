package com.alura.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idiomas;
    private int descargas;

    public Libros() {
    }

    public Libros(DatosLibros datos) {
        this.titulo = datos.titulo();
        this.idiomas = datos.idiomas().isEmpty() ? "Desconocido" : datos.idiomas().get(0);
        this.descargas = datos.descargas();


    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "\n************************" +
                "\nTítulo: " + titulo +
                "\nAutor: " + (autor != null ? autor.getName() : "Desconocido") +
                "\nIdioma: " + idiomas +
                "\nDescargas: " + descargas +
                "\n************************\n";
    }
}
