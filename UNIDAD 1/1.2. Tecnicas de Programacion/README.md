/**
 * Sistema de Gestión de Biblioteca - Demostración de Constructores
 * Autor: [Shirley Mileni Peñarreta Montenegro]
 * Arrera:[Tecnologías de la Información ]
 */
public class Libro {
    // Atributos encapsulados
    private String titulo;
    private String autor;
    private int añoPublicacion;
    private boolean disponible;

    /**
     * Constructor por defecto
     * Inicializa con valores predeterminados
     */
    public Libro() {
        this.titulo = "Desconocido";
        this.autor = "Anónimo";
        this.añoPublicacion = 2000;
        this.disponible = true;
        System.out.println("Libro creado con constructor por defecto");
    }

    /**
     * Constructor parametrizado básico (sobrecarga 1)
     * @param titulo Título del libro
     * @param autor Autor del libro
     */
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = 2020; // Valor por defecto
        this.disponible = true; // Valor por defecto
        System.out.println("Libro creado con constructor básico");
    }

    /**
     * Constructor parametrizado completo (sobrecarga 2)
     * @param titulo Título del libro
     * @param autor Autor del libro
     * @param añoPublicacion Año de publicación
     * @param disponible Estado de disponibilidad
     */
    public Libro(String titulo, String autor, int añoPublicacion, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.disponible = disponible;
        System.out.println("Libro creado con constructor completo");
    }

    // Métodos getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // ... otros getters y setters

    /**
     * Método para mostrar información del libro
     * @return String con la información formateada
     */
    public String obtenerInformacion() {
        return String.format("Título: %s | Autor: %s | Año: %d | Disponible: %s",
                titulo, autor, añoPublicacion, disponible ? "Sí" : "No");
    }

    public static void main(String[] args) {
        System.out.println(" Sistema de Gestión de Biblioteca 🏫");
        System.out.println("Demostración de Constructores en Java\n");
        
        // 1. Creación con constructor por defecto
        Libro libro1 = new Libro();
        System.out.println("Libro 1: " + libro1.obtenerInformacion());
        
        // 2. Creación con constructor básico
        Libro libro2 = new Libro("Cien años de soledad", "Gabriel García Márquez");
        System.out.println("Libro 2: " + libro2.obtenerInformacion());
        
        // 3. Creación con constructor completo
        Libro libro3 = new Libro("El principito", "Antoine de Saint-Exupéry", 1943, false);
        System.out.println("Libro 3: " + libro3.obtenerInformacion());
        
        System.out.println("\nTodos los libros fueron creados exitosamente");
    }
}
