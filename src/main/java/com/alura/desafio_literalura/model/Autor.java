package com.alura.desafio_literalura.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String anioNacimiento;
    private String anioFallecimiento;
    @ManyToMany(mappedBy = "autores", fetch = FetchType.LAZY)
    private Set<Libro> libros;

    public Autor() {
    }

    public Autor(DatosAutor autor) {
        this.nombre = autor.nombre();
        this.anioNacimiento = autor.anioNacimiento();
        this.anioFallecimiento = autor.anioFallecimiento();
    }

    public Autor(String nombre, String anioNacimiento, String anioFallecimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioFallecimiento = anioFallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(String anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(String anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "- Nombre: " + nombre  +
                ", Año nacimiento: " + anioNacimiento +
                ", Año fallecimiento: " + anioFallecimiento;
    }
}
