package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ILibroRepository extends JpaRepository<Libros, Long> {
    Optional<Libros> findByTituloAndAutor(String titulo, Autor autor);

    @Query("select l from Libros l where l.idiomas = :idioma")
    List<Libros> librosPorIdioma(@Param("idioma") String idioma);
}
