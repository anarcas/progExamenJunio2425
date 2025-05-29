
import java.util.Map;
// Importa la implementación más común
import java.util.HashMap;       
import java.util.LinkedHashMap; // Importa si necesitas mantener el orden de inserción
import java.util.TreeMap;       // Importa si necesitas las claves ordenadas
import java.util.Arrays;        // Para métodos auxiliares si usas Java 8+
import java.util.stream.Collectors; // Para Java 8+

public class DeclaracionInicioDiccionario {

    public static void main(String[] args) {

        // --- 1. Declaración e Inicialización Básica (HashMap) ---
        // Declaración: Map<TipoClave, TipoValor> nombreMapa;
        // Inicialización: new HashMap<>();
        Map<String, Integer> edades = new HashMap<>(); // Un mapa que asocia nombres (String) con edades (Integer)

        // Añadir elementos al mapa
        edades.put("Alice", 30);
        edades.put("Bob", 24);
        edades.put("Charlie", 35);

        System.out.println("Edades (HashMap): " + edades);
        System.out.println("Edad de Alice: " + edades.get("Alice"));


        // --- 2. Inicialización con valores iniciales (Java 9+) ---
        // Utilizando Map.of() o Map.ofEntries() para mapas inmutables (no se pueden modificar después de la creación)
        System.out.println("\n--- Inicialización con valores (Java 9+) ---");

        Map<String, String> capitales = Map.of(
            "España", "Madrid",
            "Francia", "París",
            "Alemania", "Berlín"
        );
        System.out.println("Capitales (Map.of()): " + capitales);
        // capitales.put("Italia", "Roma"); // Esto lanzaría UnsupportedOperationException porque es inmutable


        // --- 3. Inicialización para mapas mutables con valores iniciales (Java 8 y anteriores, o para HashMap) ---
        // Opción A: Usando un bloque de inicialización de instancia (menos común, puede tener efectos secundarios)
        Map<String, Double> precios = new HashMap<String, Double>() {{
            put("Manzana", 1.25);
            put("Pera", 0.99);
            put("Naranja", 1.50);
        }};
        System.out.println("\nPrecios (HashMap con doble llave): " + precios);

        // Opción B: Usando Stream API (más moderno para Java 8+)
        Map<String, String> colores = Arrays.asList(
            new String[][] {
                {"rojo", "#FF0000"},
                {"verde", "#00FF00"},
                {"azul", "#0000FF"}
            }
        ).stream().collect(Collectors.toMap(data -> data[0], data -> data[1]));
        System.out.println("Colores (Stream API): " + colores);


        // --- 4. Declaración e Inicialización de LinkedHashMap (mantiene el orden de inserción) ---
        System.out.println("\n--- LinkedHashMap (mantiene el orden de inserción) ---");
        Map<String, Integer> puntuaciones = new LinkedHashMap<>();
        puntuaciones.put("David", 100);
        puntuaciones.put("Ana", 75);
        puntuaciones.put("Carlos", 90);
        System.out.println("Puntuaciones (LinkedHashMap): " + puntuaciones); // El orden será: David, Ana, Carlos


        // --- 5. Declaración e Inicialización de TreeMap (ordena por clave) ---
        System.out.println("\n--- TreeMap (ordena por clave) ---");
        Map<String, Integer> codigos = new TreeMap<>();
        codigos.put("Zeta", 26);
        codigos.put("Alpha", 1);
        codigos.put("Gamma", 3);
        System.out.println("Códigos (TreeMap): " + codigos); // El orden será: Alpha, Gamma, Zeta (alfabético)

        // TreeMap con un Comparator personalizado
        Map<String, Integer> codigosDesc = new TreeMap<>((s1, s2) -> s2.compareTo(s1)); // Orden descendente
        codigosDesc.put("Zeta", 26);
        codigosDesc.put("Alpha", 1);
        codigosDesc.put("Gamma", 3);
        System.out.println("Códigos (TreeMap descendente): " + codigosDesc);
    }
}