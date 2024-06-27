package com.alura.desafio_literalura.controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.alura.desafio_literalura.model.DatosLibro;
import com.alura.desafio_literalura.model.Idiomas;
import com.alura.desafio_literalura.model.Libro;
import com.alura.desafio_literalura.repository.AutorRepository;
import com.alura.desafio_literalura.repository.LibroRepository;
import com.alura.desafio_literalura.service.ConsumoAPI;
import com.alura.desafio_literalura.service.ConvierteDatos;

import jakarta.transaction.Transactional;

public class LibroController {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;
    private Optional<Libro> libroBuscado;

    public LibroController(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public void buscarLibroPorTitulo() {
        System.out.println("""
            ********** BUSCAR LIBRO POR TÍTULO **********
            
            Escribe el nombre del libro que deseas buscar:
                """);
        try {
            var nombreLibro = teclado.nextLine();
            var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
            DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
            Libro libro = new Libro(datos, autorRepository);
            libroBuscado = libroRepository.findByTitulo(libro.getTitulo());
            if (libroBuscado.isPresent()) {
                System.out.println("- El libro ya está registrado. -");
                return;
            } else {
                libroRepository.save(libro);
                System.out.println("- Libro guardado: " + libro.getTitulo());
            }
        } catch (Exception e) {
            System.out.println("- Error al buscar el libro, intente nuevamente. -");
        }

    }

    public void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        if (libros == null || libros.isEmpty()) {
            System.out.println("- No hay libros registrados. -");
            return;
        } else {
            System.out.println("""
                ********** LIBROS REGISTRADOS **********
                    """);
            libros.forEach(libro -> System.out.println(libro));
        }
    }

    public void listarLibrosPorIdioma() {
        System.out.println("""
            ******* BUSCAR POR IDIOMA *******
                1 - Español
                2 - Inglés
                3 - Francés
                4 - Portugués
                5 - Latín
                """);
        try {
            var opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibrosPorIdioma(Idiomas.SPANISH);
                    break;
                case 2:
                    buscarLibrosPorIdioma(Idiomas.ENGLISH);
                    break;
                case 3:
                    buscarLibrosPorIdioma(Idiomas.FRENCH);
                    break;
                case 4:
                    buscarLibrosPorIdioma(Idiomas.PORTUGUES);
                    break;
                case 5:
                    buscarLibrosPorIdioma(Idiomas.LATIN);
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } catch (Exception e) {
            System.out.println("- Error al leer la opción, intente nuevamente. -");
            return;
        }

    }

    private void buscarLibrosPorIdioma(Idiomas idioma) {
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("- No hay libros registrados en el idioma seleccionado. -");
            return;
        } else {
            System.out.println("\n********** LIBROS REGISTRADOS en "+ Idiomas.toEspanol(idioma) +" **********");
            libros.forEach(libro -> System.out.println(libro));
        }
    }

    public void listarTop10LibrosMasDescargados() {
        List<Libro> libros = libroRepository.findTop10ByOrderByDescargasDesc();
        if (libros.isEmpty()) {
            System.out.println("- No hay libros registrados. -");
            return;
        } else {
            System.out.println("\n********** TOP 10 LIBROS MÁS DESCARGADOS **********");
            libros.forEach(libro -> System.out.println(libro));
        }
    }
}
