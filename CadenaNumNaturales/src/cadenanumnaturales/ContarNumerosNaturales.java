/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadenanumnaturales;

import java.util.InputMismatchException;
import java.util.LinkedHashMap; // Usamos LinkedHashMap para mantener el orden de inserción
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author anaranjo
 */
public class ContarNumerosNaturales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Declaración e iniciación de variables
        Scanner scanner = new Scanner(System.in);
        String inputLinea;
        boolean formatoValido;

        // Patrón para validar cada número individualmente:
        // ^ indica inicio de la cadena (para el match de cada número)
        // \d{1,12} indica 1 a 12 dígitos
        // $ indica fin de la cadena (para el match de cada número)
        // OJO: Este patrón se usa para cada token después de split.
        // El patrón para split será \\s+ para uno o más espacios.
        Pattern numeroPattern = Pattern.compile("^\\d{1,12}$");

        // Bucle para solicitar la entrada hasta que el formato sea correcto
        do {
            System.out.println("Introduce una cadena de texto sólo con números naturales entre 1 y 12 cifras " +
                               "separados por uno o varios espacios en blanco:");
            inputLinea = scanner.nextLine();
            // Asumimos que es válido al principio de cada iteración
            formatoValido = true; 

            // Dividir la cadena de entrada por uno o más espacios en blanco.
            // "\\s+" es una expresión regular que coincide con uno o más caracteres de espacio en blanco.
            String[] numerosStr = inputLinea.trim().split("\\s+");

            // Si la cadena está vacía o solo contiene espacios, split puede devolver un array con un elemento vacío
            if (numerosStr.length == 0 || (numerosStr.length == 1 && numerosStr[0].isEmpty())) {
                System.out.println("Error: La entrada no contiene ningún número. Inténtalo de nuevo.");
                formatoValido = false;
                // Vuelve al inicio del bucle do-while
                continue; 
            }

            // Validar cada "número" individualmente con el patrón
            for (String numStr : numerosStr) {
                // Si el token es una cadena vacía (puede ocurrir con split si hay espacios al principio/final
                // o múltiples espacios y el regex no los gestiona perfectamente para los límites),
                // o si no coincide con el patrón de 1 a 12 dígitos.
                if (numStr.isEmpty() || !numeroPattern.matcher(numStr).matches()) {
                    System.out.println("Error de formato: '" + numStr + "' no es un número válido de 1 a 12 cifras.");
                    formatoValido = false;
                    break; // Salir del bucle for y volver a pedir la entrada
                }
            }

        } while (!formatoValido); // Repetir mientras el formato no sea válido

        // Una vez que la entrada es válida, procesarla
        // Usamos LinkedHashMap para mantener el orden de inserción de los números al mostrarlos.
        // Si el orden no importa, un HashMap normal es suficiente.
        Map<Long, Integer> recuentoNumeros = new LinkedHashMap<>();

        // Volver a dividir la cadena (ya validada) para procesar los números
        String[] numerosStrParaConteo = inputLinea.trim().split("\\s+");

        for (String numStr : numerosStrParaConteo) {
            if (!numStr.isEmpty()) { // Asegurarse de no procesar cadenas vacías si split las produjo
                try {
                    Long numero = Long.parseLong(numStr);
                    // Si el número ya está en el mapa, incrementa su recuento
                    // Si no está, lo añade con un recuento de 1
                    recuentoNumeros.put(numero, recuentoNumeros.getOrDefault(numero, 0) + 1);
                } catch (NumberFormatException e) {
                    // Esto no debería ocurrir si la validación del patrón es correcta,
                    // pero es una buena práctica incluirlo.
                    System.err.println("Error interno: No se pudo convertir a número: " + numStr);
                }
            }
        }

        // Mostrar el recuento de números
        System.out.println("\nRecuento de números:");
        System.out.println("--------------------");

        recuentoNumeros.forEach((numero, recuento) -> {
            String sufijo = (recuento == 1) ? " vez" : " veces";
            // Para formatear la salida y alinear a la derecha
            System.out.printf("Número %15d -> %2d %s\n", numero, recuento, sufijo);
        });

        scanner.close();
    }

}
