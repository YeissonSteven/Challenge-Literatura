package com.alura.literatura.principal;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.DatosLibros;
import com.alura.literatura.model.Libros;
import com.alura.literatura.model.RespuestaBusqueda;
import com.alura.literatura.repository.IAutorRepository;
import com.alura.literatura.repository.ILibroRepository;
import com.alura.literatura.service.ConvierteDatos;
import com.alura.literatura.service.ObtenerDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ObtenerDatos busca =  new ObtenerDatos();
    private ConvierteDatos convierte = new ConvierteDatos();
    @Autowired
    private ILibroRepository repository;
    private List<Libros> listaDeLibros;
    private List<Autor> listaDeAutores;
    @Autowired
    private IAutorRepository repositorioAutor;

    boolean salir = false;
    public void muestraMenu(){
        while(salir == false){

            System.out.println("""
                seleccione una opcion:
                0: salir
                1: buscar por titulo
                2: mostrar libros buscados
                3: mostrar Lista de autores
                4: muestra los autores vivos en cierto agno
                5: muestra libros por idioma
                
                """);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "0":
                    salir = true;
                    break;

                case "1":
                    buscarPorTitulo();
                    break;
                case "2":
                    motrarLibrosBuscados();
                    break;
                case "3":
                    mostrarListaDeAutores();
                    break;
                case "4":
                    autoresVivosEnDeterminadoAno();
                    break;
                case "5":
                    buscarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("no es una opcion valida");
                    break;
            }

        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma de los libros a buscar:
                1- Español
                2- ingles
                3- frances
                4- portugues""");

        String idioma = sc.nextLine();
        switch (idioma) {
            case "1":
                idioma = "es";
                break;
            case "2":
                idioma = "en";
                break;
            case "3":
                idioma = "fr";
                break;
            case "4":
                idioma = "pt";
                break;
            default:
                System.out.println("no es una opcion valida");
                break;

        }
        List<Libros> librosPorIdioma = repository.librosPorIdioma(idioma);
        librosPorIdioma.forEach(System.out::println);
    }

    private void autoresVivosEnDeterminadoAno() {
        System.out.println("ingrese la fecha a buscar");
        String ano = sc.nextLine();
        try {
            List<Autor> autoresvivos =repositorioAutor.autorVivoEnCiertoAno(Integer.parseInt(ano));
            autoresvivos.forEach(autor -> autor.mostrarAutor());
        }catch (Exception e){
            System.out.println("no es una fecha valida");
        }

    }

    private void mostrarListaDeAutores() {
        listaDeAutores = repositorioAutor.findAll();
        listaDeAutores.stream()
                .forEach(Autor::mostrarAutor);
    }

    private void motrarLibrosBuscados() {
        listaDeLibros = repository.findAll();
        listaDeLibros.stream().forEach(System.out::println);
    }

    private DatosLibros buscarLibro(){
        System.out.println("introduzca el titulo del libro que desea buscar:");
        String titulo = sc.nextLine();
        String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String respuesta = busca.obtenerDatos("http://gutendex.com/books/?search=" + tituloCodificado);

        try {
            RespuestaBusqueda resultado = convierte.convertirDatos(respuesta, RespuestaBusqueda.class);
            DatosLibros datos = resultado.results().get(0);
            return datos;
        }catch (IndexOutOfBoundsException e){
            System.out.println("No se encontro el libro, asegurese de que el nombre este bien escrito");
            return null;
        }
    }

    private void buscarPorTitulo(){
        DatosLibros datos = buscarLibro();
        if(datos == null)  return;

        var datosAutor = datos.autor().get(0);

        Autor autor = repositorioAutor.findByName(datosAutor.name()).orElse(null);

        if (autor == null) {
            autor = new Autor();
            autor.setName(datosAutor.name());
            autor.setBirthYear(datosAutor.birth_year());
            autor.setDeathYear(datosAutor.death_year());
            autor = repositorioAutor.save(autor);
        }
        boolean existe = repository.findByTituloAndAutor(datos.titulo(), autor).isPresent();
        if (existe) {
            System.out.println("El libro ya está en la base de datos. No se volverá a guardar.");
            return;
        }
        Libros libro = new Libros(datos);
        libro.setAutor(autor);

        repository.save(libro);
        System.out.println("Libro guardado:");
        System.out.println(libro);




    }

}
