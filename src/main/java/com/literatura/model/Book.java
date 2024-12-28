package com.literatura.model;

import jakarta.persistence.*; // Unifica en Jakarta para evitar conflictos

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String language;
    private int downloads;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false) // Configura la clave foránea
    private Author author;

    // Constructor vacío
    public Book() {}

    // Constructor con parámetros
    public Book(String title, String language, int downloads, Author author) {
        this.title = title;
        this.language = language;
        this.downloads = downloads;
        this.author = author;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', language='" + language + "', downloads=" + downloads +
                ", author=" + author + "}";
    }
}
