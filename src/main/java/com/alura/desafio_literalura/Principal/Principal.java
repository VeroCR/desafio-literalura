package com.alura.desafio_literalura.Principal;

import com.alura.desafio_literalura.controller.AutorController;
import com.alura.desafio_literalura.controller.LibroController;

import com.alura.desafio_literalura.repository.AutorRepository;
import com.alura.desafio_literalura.repository.LibroRepository;
import java.util.Scanner;


public class Principal{
    private Scanner teclado = new Scanner(System.in);
    private LibroController libroController;
    private AutorController autorController;


    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroController = new LibroController(libroRepository, autorRepository);
        this.autorController = new AutorController(autorRepository);
    }

    public void muestraElMenu() {
        

        var opcion = -1;
        while (opcion != 0) {
            var menu = """

                    ********** LITERALURA **********
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Top 10 libros más descargados
                    0 - Salir

                    Selecciona una opción:
                    """;
            System.out.println(menu);
            try{
                opcion = teclado.nextInt();
            }catch(Exception e){
                System.out.println("- Error al leer la opción, intente nuevamente. -");
                teclado.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    libroController.buscarLibroPorTitulo();
                    break;
                case 2:
                    libroController.listarLibrosRegistrados();
                    break;
                case 3:
                    autorController.listarAutoresRegistrados();
                    break;
                case 4:
                    autorController.listarAutoresVivosEnUnDeterminadoAnio();
                    break;
                case 5:
                    libroController.listarLibrosPorIdioma();
                    break;
                case 6:
                    libroController.listarTop10LibrosMasDescargados();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

}
