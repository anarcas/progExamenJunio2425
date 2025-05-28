/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package terrenos;

/**
 * Clase abstracta Propiedad que recoge los datos necesarios para su venta.
 * 
 * @author anaranjo
 */
public abstract class Propiedad {

    // ------------------------------------------------------------------------
    // Atributos estáticos públicos (inmutables)
    // Pueden ser accedidos desde fuera de la clase
    // ------------------------------------------------------------------------
    /**
     * Precio mínimo de la propiedad: {@value PRECIO_MÍNIMO}.
     */
    public static final double PRECIO_MINIMO = 900.0;

    /**
     * Precio máximo de la propiedad: {@value PPRECIO_MÁXIMO}.
     */
    public static final double PRECIO_MAXIMO = 99_000_000.0;

    // ------------------------------------------------------------------------
    // Atributos de objeto inmutables (privados)
    // Representan el estado del objeto pero no pueden cambiar su valor
    // ------------------------------------------------------------------------
    /**
     * Nombre de la población donde se encuentra situada la propiedad.
     */
    private String localidad;

    // ------------------------------------------------------------------------
    // Atributos de objeto variables (privados)
    // Representan el estado del objeto y pueden cambiar su valor
    // ------------------------------------------------------------------------
    /**
     * Precio de la población.
     */
    private double precio;

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    // Constructor con dos parámetros
    /**
     * Constructor de dos parámetros. Crea un objeto Propiedad y almacena los
     * datos básicos de la propiedad: <strong>precio</strong> y
     * <strong>localidad</strong>.
     *
     * @param precio Precio de la propiedad.
     * @param localidad Nombre de la localidad donde se encuentra situada la
     * propiedad.
     *
     * @throws IllegalArgumentException Si el precio de la propiedad se
     * encuentra fuera del rango permitido ({PRECIO_MINIMO}, {PRECIO_MAXIMO})
     * @throws IllegalArgumentException Si el nombre de la localidad está vacío.
     * @throws NullPointerException Si el nombre de la propiedad es nulo.
     */
    protected Propiedad(double precio, String localidad) throws IllegalArgumentException, NullPointerException {
        
        // Excepciones
        if (precio < Propiedad.PRECIO_MINIMO || precio > Propiedad.PRECIO_MAXIMO) {
            throw new IllegalArgumentException(String.format("El precio de la propiedad se encuentra fuera del rango permitido [ %.2f , %.2f ].", Propiedad.PRECIO_MINIMO, Propiedad.PRECIO_MAXIMO));
        }
        if (localidad == null) {
            throw new NullPointerException("El nombre de la localidad de la propiedad no puede ser nulo.");
        }
        if (localidad.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la localidad de la propiedad no puede estar vacía.");
        }


        this.precio = precio;
        this.localidad = localidad;

    }

    /**
     * Método abstracto (sin implementación) para calcular el IVA sobre el
     * importe de venta de una propiedad.
     *
     */
    public abstract void calcularPrecioConIVA();

    /**
     * Método getter para obtener el nombre de la localidad donde está situada
     * la propiedad
     *
     * @return the localidad
     */
    public String getLocalidad() {
        return this.localidad;
    }

    /**
     * Método setter para establecer el nombre de la localidad donde está
     * situada la propiedad
     *
     * @param localidad the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Método getter para obtener el precio de la propiedad
     *
     * @return Devuelve el precio de la propiedad
     */
    public double getPrecio() {
        return this.precio;
    }

    /**
     * Método setter para establecer el precio de la propiedad
     *
     * @param precio El precio de la propiedad
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString(){
        return String.format("La propiedad situada en la localidad de %s tiene asignado un precio de venta de %.2f €.%n",this.getLocalidad(),this.getPrecio());
    }
}
