package com.alura.desafio_literalura.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.desafio_literalura.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

    Autor findAutorByNombre(String nombre);


}
