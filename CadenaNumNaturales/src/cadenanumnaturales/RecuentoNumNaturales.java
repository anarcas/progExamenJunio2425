package cadenanumnaturales;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.TreeMap;

public class RecuentoNumNaturales {

    private static final Logger logger = Logger.getLogger(RecuentoNumNaturales.class.getName());

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        // Declaración de variables
        Scanner scanner = new Scanner(System.in);
        String mensajeSalida;
        String expresion;
        String numeros;
        String[] listaNumeros;
        Map<String, Integer> libroNumeros;
        int valorActual;
        String repeticion;
        // Se establece un contador para gestionar el procesamiento del bucle Do-While
        int numVecesEsValido;

        // Iniciación de variables
        expresion = "^\\d{1,12}$";
        numVecesEsValido = 0;
        libroNumeros = new TreeMap<>();
        valorActual = 0;

        // Procesamiento del programa
        do {
            mensajeSalida = "Introduce una cadena de números separadas por espacios:";
            logger.log(Level.INFO, mensajeSalida);
            numeros = scanner.nextLine();

            // Se divide la cadena de texto introducida en partes separadas por uno o varios espacios y se almacena en una array.
            listaNumeros = numeros.split("\\s+");
            mensajeSalida = String.format("El número de elementos de la array es: %d.", listaNumeros.length);
            logger.log(Level.INFO, mensajeSalida);

            // Salida del programa
            for (String numero : listaNumeros) {

                if (estampador(numero, expresion)) {
                    numVecesEsValido++;
                    mensajeSalida = (numVecesEsValido == 1) ? String.format("La estampación del elemento de la array %s ha sido: %b y ha surgido %d vez.", numero, estampador(numero, expresion), numVecesEsValido) : String.format("La estampación del elemento de la array %s ha sido: %b y ha surgido %d veces.", numero, estampador(numero, expresion), numVecesEsValido);
                    logger.log(Level.INFO, mensajeSalida);
                } else {
                    mensajeSalida = String.format("La estampación del elemento de la array %s ha sido: %b, por lo que, se vuelve a solicitar cadena de texto al usuario.", numero, estampador(numero, expresion));
                    logger.log(Level.INFO, mensajeSalida);
                }

            }
        } while (numVecesEsValido < listaNumeros.length || listaNumeros.length > 12);

        for (String numero : listaNumeros) {
            if (libroNumeros.containsKey(numero)) {
                valorActual++;
            } else {
                valorActual = 1;
            }

            try {
                libroNumeros.put(numero, valorActual);
            } catch (NumberFormatException e) {
                mensajeSalida = "Error almacenando elemento en libroNumeros";
                logger.log(Level.SEVERE, mensajeSalida);
            }
        }

        mensajeSalida = "\n--- Inventario de números ---";
        logger.log(Level.INFO, mensajeSalida);
        for (Map.Entry<String, Integer> entrada : libroNumeros.entrySet()) {

            mensajeSalida = (entrada.getValue()==1)?String.format("Número: %-10s -> Se repite: %4d vez.%n", entrada.getKey(), entrada.getValue()):String.format("Número: %-10s -> Se repite: %4d veces.%n", entrada.getKey(), entrada.getValue());
            logger.log(Level.INFO, mensajeSalida);
        }
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
