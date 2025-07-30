readme# :books: CONSULTA INTERACTIVA DE LIBROS

![Static Badge](https://img.shields.io/badge/STATUS-FINALIZADO-green)
![GitHub Org's stars](https://img.shields.io/github/stars/YeissonSteven?style=social)

Aplicación de consola desarrollada en Java que permite buscar libros por título, consultar autores, filtrar por idioma y ver autores vivos en un año específico. Utiliza la API de [Gutendex](https://gutendex.com/) para obtener información de libros, y almacena los resultados localmente mediante JPA.

## :hammer: Funcionalidades del proyecto

- `Búsqueda de libros`: Busca libros por título desde una API externa.
- `Persistencia`: Guarda los libros y autores encontrados en base de datos usando JPA.
- `Consulta de autores`: Muestra todos los autores guardados y filtra por año de vida.
- `Filtro por idioma`: Lista libros almacenados por idioma (español, inglés, francés, portugués).
- `Evita duplicados`: Verifica si un libro ya existe en la base de datos antes de guardarlo.

## :ballot_box_with_check: Tecnologías utilizadas

- `Java 17`
- `Spring Boot`
- `Spring Data JPA`
- `Jackson`: Para deserialización de JSON
- `H2 / PostgreSQL / MySQL` (según configuración)
- `Gutendex API`: Fuente de datos externa

## :clipboard: Instrucciones de uso

1. Asegúrate de tener Java 17 o superior y una base de datos configurada.
2. Ejecuta la aplicación (`Principal.java`) desde el contexto de Spring Boot.
3. Usa el menú interactivo que aparecerá en la consola para operar la aplicación:

Seleccione una opción:
0: Salir
1: Buscar por título
2: Mostrar libros buscados
3: Mostrar lista de autores
4: Mostrar autores vivos en cierto año
5: Mostrar libros por idioma


## :warning: Requisitos previos

- Tener Java 17+ instalado.
- Tener configurado un entorno Spring Boot con dependencias de JPA y Jackson.
- Acceso a Internet para conectarse a la API de Gutendex.

## :file_folder: Estructura del Proyecto

- `Principal.java`: Clase principal con el menú interactivo.
- `ObtenerDatos.java`: Encargada de hacer las solicitudes HTTP a la API externa.
- `ConvierteDatos.java`: Usa Jackson para convertir JSON en objetos Java.
- `Libros.java`: Entidad JPA para representar libros.
- `Autor.java`: Entidad JPA para representar autores.
- `IAutorRepository.java`: Repositorio JPA para consultas de autores.
- `ILibroRepository.java`: Repositorio JPA para consultas de libros.
- `DatosLibros / RespuestaBusqueda.java`: Modelos para mapear la respuesta JSON de Gutendex.

## :camera: Capturas de pantalla

### 🏁 Menú principal de la consola
![Menú principal](assets/img.png)

### 🔍 Resultado de una búsqueda y guardado
![Resultado búsqueda](assets/img_1.png)
