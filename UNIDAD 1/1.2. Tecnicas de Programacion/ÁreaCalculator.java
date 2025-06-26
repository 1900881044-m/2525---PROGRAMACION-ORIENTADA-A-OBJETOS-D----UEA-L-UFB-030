/*
 * Programa: Calculadora de Áreas de Figuras Geométricas
 * Descripción: Este programa calcula el área de diferentes figuras geométricas
 *              (círculo, rectángulo, triángulo) según la elección del usuario.
 * Autor: [Shirley Mileni Peñarreta Montenegro ]
 * Fecha: [26/06/2025]
 */

import java.util.Scanner;

public class AreaCalculator {
    public static void main(String[] args) {
        // Crear objeto Scanner para entrada de usuario
        Scanner input_scanner = new Scanner(System.in);
        
        // Mostrar menú de opciones
        System.out.println("Calculadora de Áreas");
        System.out.println("1. Círculo");
        System.out.println("2. Rectángulo");
        System.out.println("3. Triángulo");
        System.out.print("Seleccione una figura (1-3): ");
        
        // Leer opción del usuario (tipo entero)
        int user_choice = input_scanner.nextInt();
        
        // Variables para almacenar dimensiones (tipo double para precisión)
        double dimension_one;
        double dimension_two;
        double area_result;
        
        // Bandera para validar cálculo (tipo boolean)
        boolean calculation_successful = true;
        
        switch(user_choice) {
            case 1: // Círculo
                System.out.print("Ingrese el radio del círculo: ");
                dimension_one = input_scanner.nextDouble();
                area_result = calculate_circle_area(dimension_one);
                break;
                
            case 2: // Rectángulo
                System.out.print("Ingrese la base del rectángulo: ");
                dimension_one = input_scanner.nextDouble();
                System.out.print("Ingrese la altura del rectángulo: ");
                dimension_two = input_scanner.nextDouble();
                area_result = calculate_rectangle_area(dimension_one, dimension_two);
                break;
                
            case 3: // Triángulo
                System.out.print("Ingrese la base del triángulo: ");
                dimension_one = input_scanner.nextDouble();
                System.out.print("Ingrese la altura del triángulo: ");
                dimension_two = input_scanner.nextDouble();
                area_result = calculate_triangle_area(dimension_one, dimension_two);
                break;
                
            default:
                System.out.println("Opción no válida.");
                calculation_successful = false;
                area_result = 0; // Valor por defecto
        }
        
        // Mostrar resultado si el cálculo fue exitoso
        if (calculation_successful) {
            System.out.println("El área calculada es: " + area_result);
        }
        
        // Cerrar scanner
        input_scanner.close();
    }
    
    /**
     * Calcula el área de un círculo
     * @param radius Radio del círculo
     * @return Área del círculo
     */
    public static double calculate_circle_area(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }
    
    /**
     * Calcula el área de un rectángulo
     * @param base Base del rectángulo
     * @param height Altura del rectángulo
     * @return Área del rectángulo
     */
    public static double calculate_rectangle_area(double base, double height) {
        return base * height;
    }
    
    /**
     * Calcula el área de un triángulo
     * @param base Base del triángulo
     * @param height Altura del triángulo
     * @return Área del triángulo
     */
    public static double calculate_triangle_area(double base, double height) {
        return (base * height) / 2;
    }
}
