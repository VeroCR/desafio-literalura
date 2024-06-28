# Desafío Literalura

## Descripción

El proyecto Desafío Literatura es una aplicación Java diseñada para gestionar una biblioteca digital, permitiendo a los usuarios interactuar con una colección de libros y autores. Utiliza una arquitectura basada en el patrón MVC (Modelo-Vista-Controlador) para separar la lógica de la aplicación, la interfaz de usuario y la manipulación de datos.

## Funcionalidades Principales

- **Libros**: Permite a los usuarios guardar libros buscados utilizando la API de Gutendex, así como listarlos.
- **Autores**: Permite guardar los autores de los libros buscados así como la información de nacimiento y fallecimiento, así como listarlos.
- **Búsqueda y Filtro**: Los usuarios pueden buscar libros y autores específicos y filtrar resultados basados en criterios como género, año de publicación, etc.
- **Interfaz de Usuario**: Proporciona una interfaz simple para interactuar con el sistema a través de la línea de comandos.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal utilizado para desarrollar la lógica de la aplicación.
- **Spring Boot**: Marco de trabajo utilizado para simplificar el desarrollo de aplicaciones y servicios con Spring.
- **JPA/Hibernate**: Para la persistencia de datos y la interacción con la base de datos.
- **PostgreSQL**: Base de datos utilizada para el desarrollo.

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

- `/src/main/java`: Contiene el código fuente de la aplicación, incluyendo modelos, vistas, controladores y la clase principal de la aplicación.
- `/src/main/resources`: Alberga archivos de configuración y recursos estáticos necesarios para la aplicación.

## Cómo Empezar

Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

1. Clona el repositorio en tu máquina local.
2. Asegúrate de tener Java JDK 11 o superior instalado.
3. Abre el proyecto en tu IDE.
4. Ejecuta el proyecto a través de tu IDE o utilizando el comando `mvn spring-boot:run` en la terminal.
5. Interactúa con la aplicación a través de la línea de comandos.
