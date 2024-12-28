package com.literatura;

import com.fasterxml.jackson.databind.JsonNode;
import com.literatura.client.GutendexClient;
import com.literatura.model.Author;
import com.literatura.model.Book;
import com.literatura.repository.AuthorRepository;
import com.literatura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.ssl.SslAutoConfiguration;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication(exclude = {SslAutoConfiguration.class})
public class Main implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final GutendexClient gutendexClient = new GutendexClient();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        do {
            try {
                showMenu();
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                handleOption(option, scanner);
            } catch (InputMismatchException e) {
                System.out.println("Error: Opción inválida. Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar entrada no válida
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                e.printStackTrace(); // Para depuración
            }
        } while (option != 0);
    }

    private void handleOption(int option, Scanner scanner) throws Exception {
        switch (option) {
            case 1 -> {
                System.out.print("Buscar Libro por Título: ");
                String title = scanner.nextLine().trim();
                if (title.isEmpty()) {
                    System.out.println("Error: El título no puede estar vacío.");
                } else {
                    searchBookByTitle(title);
                }
            }
            case 2 -> {
                System.out.print("Buscar por Nombre de Autor: ");
                String authorName = scanner.nextLine().trim();
                if (authorName.isEmpty()) {
                    System.out.println("Error: El nombre del autor no puede estar vacío.");
                } else {
                    searchAuthorByName(authorName);
                }
            }
            case 3 -> listBooks();
            case 4 -> listAuthors();
            case 0 -> System.out.println("Cerrando aplicación... ¡Adiós!");
            default -> System.out.println("Error: Opción inválida. Intente nuevamente.");
        }
    }

    private void showMenu() {
        System.out.printf("""
                
        ¡¡BIENVENIDO A LITERATURA!!
        1 - Buscar libro por título
        2 - Buscar autor por nombre
        3 - Listar libros registrados
        4 - Listar autores registrados
        0 - Salir
                
        -> Seleccione una opción: """);
    }

    private void searchBookByTitle(String title) {
        try {
            List<Book> books = bookRepository.findByTitle(title);
            if (books.isEmpty()) {
                System.out.println("No se encontraron libros con el título: " + title);
            } else {
                Book foundBook = books.get(0);
                System.out.printf("""
                        
                        =============================================================================
                        Título: %s
                        Autor: %s
                        Idiomas: %s
                        Número de Descargas: %d
                        =============================================================================
                        """,
                        foundBook.getTitle(),
                        foundBook.getAuthor().getName(),
                        foundBook.getLanguage(),
                        foundBook.getDownloads());
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    private void searchAuthorByName(String authorName) {
        try {
            Author author = authorRepository.findByName(authorName);
            if (author == null) {
                System.out.println("No se encontraron autores con el nombre: " + authorName);
            } else {
                System.out.printf("""
                        
                        ===================================================================
                        Nombre: %s
                        Año de nacimiento: %d
                        Año de fallecimiento: %d
                        Libros:
                        """, author.getName(), author.getBirthYear(), author.getDeathYear());

                List<Book> booksByAuthor = bookRepository.findByAuthor(author);
                if (booksByAuthor.isEmpty()) {
                    System.out.println("No se encontraron libros para el autor: " + authorName);
                } else {
                    booksByAuthor.forEach(book -> System.out.println("- " + book.getTitle()));
                }
                System.out.println("===================================================================");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el autor: " + e.getMessage());
        }
    }

    private void listBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            if (books.isEmpty()) {
                System.out.println("No hay libros registrados.");
            } else {
                books.forEach(book -> System.out.printf("""
                        
                        ==============================================
                        Título: %s
                        Autor: %s
                        Idiomas: %s
                        Número de Descargas: %d
                        ==============================================
                        """, book.getTitle(), book.getAuthor().getName(), book.getLanguage(), book.getDownloads()));
            }
        } catch (Exception e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
        }
    }

    private void listAuthors() {
        try {
            List<Author> authors = authorRepository.findAll();
            if (authors.isEmpty()) {
                System.out.println("No hay autores registrados.");
            } else {
                authors.forEach(author -> System.out.printf("""
                        
                        ==============================================
                        Nombre: %s
                        Año de nacimiento: %d
                        Año de fallecimiento: %d
                        ==============================================
                        """, author.getName(), author.getBirthYear(), author.getDeathYear()));
            }
        } catch (Exception e) {
            System.out.println("Error al listar los autores: " + e.getMessage());
        }
    }
}
