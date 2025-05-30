package cadenanumnaturales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.TreeMap;

public class RecuentoNumNaturalesArchivo {

    private static final Logger logger = Logger.getLogger(RecuentoNumNaturalesArchivo.class.getName());

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        // Declaración de variables
        Scanner scanner = new Scanner(System.in);
        String mensajeSalida;
        String expresion;
        String expresion2;
        String cadenas;
        String[] listaCadenas;
        Map<String, Integer> libroNumeros;
        Map<String, Integer> libroPalabras;
        int valorActual;
        String linea;

        // Se establece un contador para gestionar el procesamiento del bucle Do-While
        int numVecesEsValido;
        // Ruta del archivo
        String rutaArchivo;

        // Iniciación de variables
        expresion = "^\\d+$";
        expresion2 = "^[a-zA-Z]+$";
        numVecesEsValido = 0;
        libroNumeros = new TreeMap<>();
        libroPalabras = new TreeMap<>();
        valorActual = 0;

        // Procesamiento del programa
        do {
            mensajeSalida = "Introduce una cadena de números separadas por espacios:";
            logger.log(Level.INFO, mensajeSalida);
            cadenas = scanner.nextLine();

            // Se divide la cadena de texto introducida en partes separadas por uno o varios espacios y se almacena en una array.
            listaCadenas = cadenas.split("\\s+");
            mensajeSalida = String.format("El número de elementos de la array es: %d.", listaCadenas.length);
            logger.log(Level.INFO, mensajeSalida);

            for (String cadena : listaCadenas) {

                if (estampador(cadena, expresion)) {
                    numVecesEsValido++;

                    if (libroNumeros.containsKey(cadena)) {
                        valorActual++;
                    } else {
                        valorActual = 1;
                    }

                    try {
                        libroNumeros.put(cadena, valorActual);
                    } catch (NumberFormatException e) {
                        mensajeSalida = "Error almacenando elemento en libroNumeros";
                        logger.log(Level.SEVERE, mensajeSalida);
                    }

                } else if (estampador(cadena, expresion2)) {
                    numVecesEsValido++;

                    if (libroPalabras.containsKey(cadena)) {
                        valorActual++;
                    } else {
                        valorActual = 1;
                    }

                    try {
                        libroPalabras.put(cadena, valorActual);
                    } catch (NumberFormatException e) {
                        mensajeSalida = "Error almacenando elemento en libroPalabras";
                        logger.log(Level.SEVERE, mensajeSalida);
                    }

                } else {
                    mensajeSalida = String.format("La estampación del elemento de la array %s ha sido incorrecto, se vuelve a solicitar cadena de texto al usuario.", cadena);
                    logger.log(Level.INFO, mensajeSalida);
                }

            }
            System.out.printf("Número de veces que la estampación es válida: %d%n", numVecesEsValido);
        } while (numVecesEsValido < listaCadenas.length || listaCadenas.length > 12);

        for (Map.Entry<String, Integer> entrada : libroNumeros.entrySet()) {

            mensajeSalida = (entrada.getValue() == 1) ? String.format("Número: %-10s -> Se repite: %4d vez.%n", entrada.getKey(), entrada.getValue()) : String.format("Número: %-10s -> Se repite: %4d veces.%n", entrada.getKey(), entrada.getValue());
            rutaArchivo = String.format("%sarchivoNumeros.txt", System.getProperty("user.dir"));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
                bw.write(mensajeSalida);
            } catch (IOException e) {
                logger.log(Level.SEVERE, String.format("Error de escritura: %s", e.getMessage()));
            }
        }

        for (Map.Entry<String, Integer> entrada : libroPalabras.entrySet()) {

            mensajeSalida = (entrada.getValue() == 1) ? String.format("Palabra: %-10s -> Se repite: %4d vez.%n", entrada.getKey(), entrada.getValue()) : String.format("Palabra: %-10s -> Se repite: %4d veces.%n", entrada.getKey(), entrada.getValue());
            rutaArchivo = String.format("%sarchivoPalabras.txt", System.getProperty("user.dir"));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
                bw.write(mensajeSalida);
            } catch (IOException e) {
                logger.log(Level.SEVERE, String.format("Error de escritura: %s", e.getMessage()));
            }
        }

        // Imprimir el contenido de los archivos por pantalla
        rutaArchivo = String.format("%sarchivoPalabras.txt", System.getProperty("user.dir"));
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Leer línea por línea hasta que no haya más líneas (br.readLine() devuelve null)
            while ((linea = br.readLine()) != null) {
                // Imprime la línea leída por pantalla
                System.out.println(linea);
            }
            System.out.println("--- Fin de la lectura LIBRO PALABRAS ---");

        } catch (IOException e) {
            // Manejo de la excepción si ocurre un error de lectura (ej. archivo no encontrado, permisos)
            logger.log(Level.SEVERE, String.format("Error de lectura: %s", e.getMessage()));
        }
        rutaArchivo = String.format("%sarchivoNumeros.txt", System.getProperty("user.dir"));
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Leer línea por línea hasta que no haya más líneas (br.readLine() devuelve null)
            while ((linea = br.readLine()) != null) {
                // Imprime la línea leída por pantalla
                System.out.println(linea);
            }
            System.out.println("--- Fin de la lectura LIBRO NÚMEROS ---");

        } catch (IOException e) {
            // Manejo de la excepción si ocurre un error de lectura (ej. archivo no encontrado, permisos)
            logger.log(Level.SEVERE, String.format("Error de lectura: %s", e.getMessage()));

        }

        mensajeSalida = "\n--- Archivos cerrados ---";
        logger.log(Level.INFO, mensajeSalida);
    }

    /**
     * Método estampador útil para hacer match sobre cadenas de caracteres.
     * Recibe una cadena de caracteres y un patron, devolviendo un boleano.
     *
     * @param cadena
     * @param expresion
     * @return Verdadero o false, dependiendo si cumple el patrón.
     */
    private static boolean estampador(String cadena, String expresion) {

        Pattern patron = Pattern.compile(expresion);
        Matcher acoplamiento = patron.matcher(cadena);

        return acoplamiento.matches();

    }
}
