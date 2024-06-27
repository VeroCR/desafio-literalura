package com.alura.desafio_literalura.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import com.alura.desafio_literalura.repository.AutorRepository;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "libro_autor", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores;
    @Enumerated(EnumType.STRING)
    private Idiomas idioma;
    private Integer descargas;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro, AutorRepository autorRepository) {
        this.autores = new HashSet<>();
        this.titulo = datosLibro.titulo();
        System.out.println("titulo: " + this.titulo);
        this.idioma = Idiomas.fromString(datosLibro.idioma().get(0).toString());
        System.out.println("idioma: " + this.idioma);
        this.descargas = datosLibro.descargas();
        System.out.println("descargas: " + this.descargas);
        datosLibro.autores().forEach(datoAutor -> {
            // Usar el método asegurarAutorExistente para obtener un autor existente o crear
            // uno nuevo
            Autor autor = asegurarAutorExistente(datoAutor, autorRepository);
            System.out.println("autor: " + autor.getNombre());
            System.out.println("anio nacimiento: " + autor.getAnioNacimiento());
            System.out.println("anio fallecimiento: " + autor.getAnioFallecimiento());
            this.autores.add(autor);
        });
    }

    private Autor asegurarAutorExistente(DatosAutor datosAutor, AutorRepository autorRepository) {
        Autor autorExistente = autorRepository.findAutorByNombre(datosAutor.nombre());
        if (autorExistente != null) {
            return autorExistente;
        } else {
            Autor nuevoAutor = new Autor(datosAutor.nombre(), datosAutor.anioNacimiento(),
                    datosAutor.anioFallecimiento());
            autorRepository.save(nuevoAutor);
            return nuevoAutor;
        }
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Idiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return 
                "\nId: " + Id +
                "\nTítulo: " + titulo +
                "\nAutores: " + autores +
                "\nIdioma: " + Idiomas.toEspanol(idioma) +
                "\nDescargas: " + descargas +
                "\n___________________________\n";
    }
}
