// Programa: Aplicación de conceptos de POO en Java
// Autor: Shirley Mileni Peñarreta Montenegro
// Descripción: Este programa demuestra herencia, encapsulación y polimorfismo.

public class POO_Tarea {

    // Clase base Animal
    static class Animal {
        // Encapsulación: atributos privados
        private String nombre;
        private int edad;

        // Constructor
        public Animal(String nombre, int edad) {
            this.nombre = nombre;
            this.edad = edad;
        }

        // Getters y Setters (encapsulación)
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            this.edad = edad;
        }

        // Método que será sobrescrito (polimorfismo)
        public void hacerSonido() {
            System.out.println("El animal hace un sonido genérico.");
        }

        // Método para mostrar información
        public void mostrarInfo() {
            System.out.println("Nombre: " + nombre + ", Edad: " + edad);
        }
    }

    // Clase derivada Perro que hereda de Animal (Herencia)
    static class Perro extends Animal {
        private String raza;

        // Constructor
        public Perro(String nombre, int edad, String raza) {
            super(nombre, edad); // Llama al constructor de la clase base
            this.raza = raza;
        }

        // Getter y Setter para raza
        public String getRaza() {
            return raza;
        }

        public void setRaza(String raza) {
            this.raza = raza;
        }

        // Sobrescritura del método hacerSonido (Polimorfismo)
        @Override
        public void hacerSonido() {
            System.out.println(getNombre() + " dice: ¡Guau guau!");
        }
    }

    // Clase derivada Gato que hereda de Animal (Herencia)
    static class Gato extends Animal {
        private String color;

        // Constructor
        public Gato(String nombre, int edad, String color) {
            super(nombre, edad); // Llama al constructor de la clase base
            this.color = color;
        }

        // Getter y Setter para color
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        // Sobrescritura del método hacerSonido (Polimorfismo)
        @Override
        public void hacerSonido() {
            System.out.println(getNombre() + " dice: ¡Miau miau!");
        }
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        // Crear instancia de Perro
        Perro miPerro = new Perro("Max", 4, "Labrador");
        miPerro.mostrarInfo();
        miPerro.hacerSonido();

        // Crear instancia de Gato
        Gato miGato = new Gato("Mia", 2, "Blanco");
        miGato.mostrarInfo();
        miGato.hacerSonido();

        // Demostrar encapsulación: modificar atributos con setters
        miPerro.setEdad(5);
        System.out.println(miPerro.getNombre() + " ahora tiene " + miPerro.getEdad() + " años.");
    }
}
