import java.util.*;

class Libro {
    private final String isbn;
    private final String titulo;
    private final String autor;
    private final String categoria;

    public Libro(String isbn, String titulo, String autor, String categoria) {
        if (isbn == null || isbn.isEmpty() || titulo == null || titulo.isEmpty() ||
            autor == null || autor.isEmpty() || categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("Datos del libro inválidos");
        }
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return "[" + isbn + "] " + titulo + " - " + autor + " (" + categoria + ")";
    }
}

class Usuario {
    private String id;
    private String nombre;
    private List<String> isbnsPrestados;

    public Usuario(String id, String nombre) {
        if (id == null || id.isEmpty() || nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("Datos del usuario inválidos");
        }
        this.id = id;
        this.nombre = nombre;
        this.isbnsPrestados = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<String> getIsbnsPrestados() { return isbnsPrestados; }

    public void prestarLibro(String isbn) {
        isbnsPrestados.add(isbn);
    }

    public void devolverLibro(String isbn) {
        isbnsPrestados.remove(isbn);
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}

class Biblioteca {
    private Map<String, Libro> catalogoPorIsbn;
    private Map<String, Usuario> usuariosPorId;
    private Set<String> isbnsPrestados;

    public Biblioteca() {
        catalogoPorIsbn = new HashMap<>();
        usuariosPorId = new HashMap<>();
        isbnsPrestados = new HashSet<>();
    }

    // Añadir libro
    public void añadirLibro(Libro libro) {
        if (catalogoPorIsbn.containsKey(libro.getIsbn())) {
            System.out.println("El libro ya existe en el catálogo.");
        } else {
            catalogoPorIsbn.put(libro.getIsbn(), libro);
            System.out.println("Libro añadido: " + libro);
        }
    }

    // Quitar libro
    public void quitarLibro(String isbn) {
        if (isbnsPrestados.contains(isbn)) {
            System.out.println("No se puede quitar, el libro está prestado.");
            return;
        }
        if (catalogoPorIsbn.remove(isbn) != null) {
            System.out.println("Libro eliminado con ISBN: " + isbn);
        } else {
            System.out.println("El libro no existe.");
        }
    }

    // Registrar usuario
    public void registrarUsuario(Usuario u) {
        if (usuariosPorId.containsKey(u.getId())) {
            System.out.println("Usuario ya registrado.");
        } else {
            usuariosPorId.put(u.getId(), u);
            System.out.println("Usuario registrado: " + u);
        }
    }

    // Dar de baja usuario
    public void darBajaUsuario(String id) {
        Usuario u = usuariosPorId.get(id);
        if (u == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }
        if (!u.getIsbnsPrestados().isEmpty()) {
            System.out.println("No se puede dar de baja, tiene libros prestados.");
            return;
        }
        usuariosPorId.remove(id);
        System.out.println("Usuario dado de baja: " + id);
    }

    // Prestar libro
    public void prestarLibro(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        Libro l = catalogoPorIsbn.get(isbn);
        if (u == null || l == null) {
            System.out.println("Usuario o libro no encontrado.");
            return;
        }
        if (isbnsPrestados.contains(isbn)) {
            System.out.println("El libro ya está prestado.");
            return;
        }
        u.prestarLibro(isbn);
        isbnsPrestados.add(isbn);
        System.out.println("Libro prestado a " + u.getNombre() + ": " + l.getTitulo());
    }

    // Devolver libro
    public void devolverLibro(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        if (u == null || !u.getIsbnsPrestados().contains(isbn)) {
            System.out.println("No existe el préstamo.");
            return;
        }
        u.devolverLibro(isbn);
        isbnsPrestados.remove(isbn);
        System.out.println("Libro devuelto: " + isbn);
    }

    // Buscar por título
    public List<Libro> buscarPorTitulo(String texto) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro l : catalogoPorIsbn.values()) {
            if (l.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                resultados.add(l);
            }
        }
        return resultados;
    }

    // Buscar por autor
    public List<Libro> buscarPorAutor(String texto) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro l : catalogoPorIsbn.values()) {
            if (l.getAutor().toLowerCase().contains(texto.toLowerCase())) {
                resultados.add(l);
            }
        }
        return resultados;
    }

    // Buscar por categoría
    public List<Libro> buscarPorCategoria(String texto) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro l : catalogoPorIsbn.values()) {
            if (l.getCategoria().toLowerCase().contains(texto.toLowerCase())) {
                resultados.add(l);
            }
        }
        return resultados;
    }

    // Listar libros prestados de un usuario
    public List<Libro> listarPrestados(String idUsuario) {
        Usuario u = usuariosPorId.get(idUsuario);
        List<Libro> librosPrestados = new ArrayList<>();
        if (u != null) {
            for (String isbn : u.getIsbnsPrestados()) {
                librosPrestados.add(catalogoPorIsbn.get(isbn));
            }
        }
        return librosPrestados;
    }
}

public class SistemaBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Registrar usuarios
        biblioteca.registrarUsuario(new Usuario("U1", "Ana"));
        biblioteca.registrarUsuario(new Usuario("U2", "Luis"));

        // Añadir libros
        biblioteca.añadirLibro(new Libro("ISBN-001", "Clean Code", "Robert C. Martin", "Software"));
        biblioteca.añadirLibro(new Libro("ISBN-002", "Effective Java", "Joshua Bloch", "Java"));

        // Prestar libros
        biblioteca.prestarLibro("U1", "ISBN-001");
        biblioteca.prestarLibro("U2", "ISBN-002");

        // Listar libros prestados por U1
        System.out.println("Libros prestados por U1: " + biblioteca.listarPrestados("U1"));

        // Devolver libro
        biblioteca.devolverLibro("U1", "ISBN-001");

        // Buscar por autor
        System.out.println("Buscar por autor 'Bloch': " + biblioteca.buscarPorAutor("Bloch"));

        // Buscar por categoría
        System.out.println("Buscar por categoría 'Software': " + biblioteca.buscarPorCategoria("Software"));
    }
}
