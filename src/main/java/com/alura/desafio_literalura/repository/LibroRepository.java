package com.alura.desafio_literalura.repository;

import com.alura.desafio_literalura.model.Idiomas;
import com.alura.desafio_literalura.model.Libro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);

    List<Libro> findByIdioma(Idiomas idioma);

    List<Libro> findTop10ByOrderByDescargasDesc();

}
