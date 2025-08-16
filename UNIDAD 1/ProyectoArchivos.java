import java.io.*;

/**
 * Clase que gestiona operaciones de lectura y escritura de archivos.
 */
class GestorDeArchivo {

    /**
     * Guarda contenido en un archivo de texto.
     * @param nombreArchivo Nombre del archivo.
     * @param contenido Contenido a escribir.
     */
    public void guardar(String nombreArchivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee el contenido de un archivo línea por línea.
     * @param nombreArchivo Nombre del archivo.
     * @return Contenido completo del archivo.
     */
    public String leer(String nombreArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return contenido.toString();
    }
}

/**
 * Excepción personalizada que se lanza cuando un archivo está vacío.
 */
class ArchivoVacioException extends Exception {
    public ArchivoVacioException(String mensaje) {
        super(mensaje);
    }
}

/**
 * Clase que valida si un archivo contiene contenido.
 */
class ValidadorArchivo {

    /**
     * Verifica que el archivo no esté vacío.
     * @param nombreArchivo Nombre del archivo.
     * @throws ArchivoVacioException Si el archivo está vacío.
     */
    public void verificarNoVacio(String nombreArchivo) throws ArchivoVacioException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            if (reader.readLine() == null) {
                throw new ArchivoVacioException("El archivo está vacío.");
            }
        } catch (IOException e) {
            System.out.println("Error al verificar el archivo: " + e.getMessage());
        }
    }
}

/**
 * Clase principal que prueba las funcionalidades de lectura, escritura y validación de archivos.
 */
public class ProyectoArchivos {
    public static void main(String[] args) {
        String nombreArchivo = "archivo.txt";
        String contenido = "Hola Milena, este es tu archivo de prueba.\n¡Buena suerte con tu tarea!";

        GestorDeArchivo gestor = new GestorDeArchivo();
        ValidadorArchivo validador = new ValidadorArchivo();

        // Guardar contenido en el archivo
        gestor.guardar(nombreArchivo, contenido);

        // Leer contenido del archivo
        String textoLeido = gestor.leer(nombreArchivo);
        System.out.println("Contenido del archivo:\n" + textoLeido);

        // Verificar si el archivo está vacío
        try {
            validador.verificarNoVacio(nombreArchivo);
            System.out.println("El archivo contiene información.");
        } catch (ArchivoVacioException ave) {
            System.out.println("Error: " + ave.getMessage());
        }
    }
}
