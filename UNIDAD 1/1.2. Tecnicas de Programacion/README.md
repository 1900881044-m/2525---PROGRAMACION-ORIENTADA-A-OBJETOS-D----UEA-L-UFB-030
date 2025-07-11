// Clase Persona
// Autor: Shirley Mileni Peñarreta Montenegro
// Descripción: Esta clase representa una persona con nombre, edad y carrera.
// Demuestra el uso de constructores por defecto, parametrizados y sobrecarga.

public class Persona {
    // Atributos privados (encapsulamiento)
    private String nombre;
    private int edad;
    private String carrera;

    // Constructor por defecto
    // Inicializa los atributos con valores genéricos
    public Persona() {
        this.nombre = "No definido";
        this.edad = 0;
        this.carrera = "No especificada";
    }

    // Constructor parametrizado completo
    // Permite inicializar todos los atributos al crear el objeto
    public Persona(String nombre, int edad, String carrera) {
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = carrera;
    }

    // Sobrecarga de constructor
    // Inicializa solo nombre y edad, asigna un valor por defecto a carrera
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = "Carrera no especificada";
    }

    // Método para mostrar la información de la persona
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Carrera: " + carrera);
        System.out.println("----------------------------");
    }
}
// Clase principal (Main)
// Autor: Shirley Mileni Peñarreta Montenegro
// Descripción: Crea instancias de la clase Persona usando diferentes constructores

public class Main {
    public static void main(String[] args) {
        // Crear un objeto usando el constructor por defecto
        Persona persona1 = new Persona();
        System.out.println("Persona 1 (Constructor por defecto):");
        persona1.mostrarInformacion();

        // Crear un objeto usando el constructor parametrizado completo
        Persona persona2 = new Persona("Shirley Mileni Peñarreta Montenegro", 18, "Tecnologías de la Información");
        System.out.println("Persona 2 (Constructor parametrizado):");
        persona2.mostrarInformacion();

        // Crear un objeto usando el constructor sobrecargado
        Persona persona3 = new Persona("Carlos López", 22);
        System.out.println("Persona 3 (Constructor sobrecargado):");
        persona3.mostrarInformacion();
    }
}
    
