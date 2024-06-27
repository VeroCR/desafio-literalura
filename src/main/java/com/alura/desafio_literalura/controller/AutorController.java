package com.alura.desafio_literalura.controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.alura.desafio_literalura.model.Autor;
import com.alura.desafio_literalura.repository.AutorRepository;

public class AutorController {
    private AutorRepository autorRepository;
    private List<Autor> autores;
    private Scanner teclado = new Scanner(System.in);

    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void listarAutoresRegistrados() {

        autores = autorRepository.findAll();
        if (autores == null || autores.isEmpty()) {
            System.out.println("- No hay autores registrados. -");
            return;
        } else {
            Map<String, List<Autor>> autoresRegistrados = autores.stream()
                    .collect(Collectors.groupingBy(Autor::getNombre));
            if (autoresRegistrados.isEmpty()) {
                System.out.println("- No hay autores registrados. -");
                return;
            } else {
                System.out.println("\n********** AUTORES REGISTRADOS **********");
                listarAutores(autoresRegistrados.values().stream().flatMap(List::stream).collect(Collectors.toList()));

            }
        }
    }

    public void listarAutoresVivosEnUnDeterminadoAnio() {
        System.out.println("Escribe el año que deseas buscar");
        try{
            var anio = teclado.nextInt();
        autores = autorRepository.findAll();
        if(autores == null || autores.isEmpty()){
            System.out.println("No hay autores registrados");
            return;
        }else{
            Map<String, List<Autor>> autoresVivos = autores.stream().filter(autor -> autor.getAnioNacimiento() != null && autor.getAnioFallecimiento() != null && Integer.parseInt(autor.getAnioNacimiento()) <= anio && Integer.parseInt(autor.getAnioFallecimiento()) >= anio)
                    .collect(Collectors.groupingBy(Autor::getNombre));
            if(autoresVivos.isEmpty()){
                System.out.println("No hay autores vivos en el año " + anio);
                return;
            }else{
                System.out.println("\n********** AUTORES VIVOS EN EL AÑO " + anio + " **********");
                listarAutores(autoresVivos.values().stream().flatMap(List::stream).collect(Collectors.toList()));
            }
        }
        } catch (Exception e) {
            System.out.println("- Error al leer el año, intente nuevamente. -");
        }
        
    }

    public void listarAutores(List<Autor> listaAutores) {
        listaAutores.forEach(autor -> System.out.println("- Nombre: " + autor.getNombre() + ", "
                + " - Año de nacimiento: " + autor.getAnioNacimiento()
                + " - Año de fallecimiento: " + autor.getAnioFallecimiento()));
    }
}
