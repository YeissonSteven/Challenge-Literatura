package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByName(String name);
    @Query("SELECT a FROM Autor a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Autor> autorVivoEnCiertoAno(@Param("year") int year);
}
