/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package terrenos;

/**
 * Clase Terrero que hereda de la clase abstracta Propiedad e implementa de la
 * interfaz Representable.
 *
 * @author anaranjo
 */
public class Terreno extends Propiedad implements Representable {

    private double base;
    private double altura;
    public static final double BASE_MIN = 0.010;
    public static final double BASE_MAX = 10.0;
    public static final double ALTURA_MIN = 0.010;
    public static final double ALTURA_MAX = 10.0;
    private double iva;
    public static final double IVA_MIN = 0.0;
    public static final double IVA_MAX = 0.21;

    public Terreno(double precio, String localidad, double base, double altura, double iva) throws IllegalArgumentException, NullPointerException {
        super(precio, localidad);

        if (base < Terreno.BASE_MIN || base > Terreno.BASE_MAX) {
            throw new IllegalArgumentException(String.format("El valor introducido de la base es incorrecto, debe estar comprendido entre [ %.2f , %.2f ]", Terreno.BASE_MIN, Terreno.BASE_MAX));
        }
        if (altura < Terreno.ALTURA_MIN || altura > Terreno.ALTURA_MAX) {
            throw new IllegalArgumentException(String.format("El valor introducido de la altura es incorrecto, debe estar comprendido entre [ %.2f , %.2f ]", Terreno.ALTURA_MIN, Terreno.ALTURA_MAX));
        }
        if (iva < Terreno.IVA_MIN || altura > Terreno.IVA_MAX) {
            throw new IllegalArgumentException(String.format("El valor introducido del IVA es incorrecto, debe estar comprendido entre [ %.2f , %.2f ]", Terreno.IVA_MIN, Terreno.IVA_MAX));
        }
        this.base = base;
        this.altura = altura;
        this.iva = iva;
    }

    @Override
    public double calcularPrecioConIVA() {
        return this.precio * (1 + getIva());
    }

    @Override
    public String representar() {
                
        int baseRedondeada = (int) Math.round(this.base);
        int alturaRedondeada = (int) Math.round(this.altura);

        // Si el redondeo es 0, se considera 1 para poder representar "algo"
        if (baseRedondeada == 0) {
            baseRedondeada = 1;
        }
        if (alturaRedondeada == 0) {
            alturaRedondeada = 1;
        }

        StringBuilder sb = new StringBuilder();

        // Primera fila
        for (int i = 0; i < baseRedondeada; i++) {
            sb.append("*");
        }
        sb.append("\n");

        // Filas intermedias
        if (alturaRedondeada > 1) {
            for (int i = 0; i < alturaRedondeada - 2; i++) {
                sb.append("*");
                for (int j = 0; j < baseRedondeada - 2; j++) {
                    sb.append(" ");
                }
                // Asegura que no se agregue el segundo '*' si baseRedondeada es 1
                if (baseRedondeada > 1) {
                    sb.append("*");
                }
                sb.append("\n");
            }
        }


        // Última fila (solo si alturaRedondeada es mayor que 1)
        if (alturaRedondeada > 1) {
            for (int i = 0; i < baseRedondeada; i++) {
                sb.append("*");
            }
            sb.append("\n");
        }

        return sb.toString();


    }

    /**
     * Calcula la extensión del terreno en metros cuadrados.
     *
     * @return La extensión del terreno en metros cuadrados.
     */
    public double getExtension() {

        return this.base * 1000 * this.altura * 1000;

    }

    /**
     * @return the base
     */
    public double getBase() {
        return this.base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(double base) {
        if (base < Terreno.BASE_MIN || base > Terreno.BASE_MAX) {
            throw new IllegalArgumentException("La base del terreno debe estar entre " +
                    Terreno.BASE_MIN + " y " + Terreno.BASE_MAX + " kilómetros.");
        }
        this.base = base;
    }

    /**
     * @return the altura
     */
    public double getAltura() {
        return this.altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        if (altura < Terreno.ALTURA_MIN || altura > Terreno.ALTURA_MAX) {
            throw new IllegalArgumentException("La altura del terreno debe estar entre " +
                    Terreno.ALTURA_MIN + " y " + Terreno.ALTURA_MAX + " kilómetros.");
        }
        this.altura = altura;
    }

    /**
     * @return the iva
     */
    public double getIva() {
        return this.iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(double iva) {
        if (iva < Terreno.IVA_MIN || iva > Terreno.IVA_MAX) {
            throw new IllegalArgumentException("El IVA no puede ser negativo ni superior al " + (Terreno.IVA_MAX * 100) + "%.");
        }
        this.iva = iva;
    }
        @Override
        public String toString() {
        return super.toString() +
                String.format("Base: %.2f km, Altura: %.2f km y Extensión: %.2f km2.",this.base,this.altura, this.getExtension());
    }
    
}
