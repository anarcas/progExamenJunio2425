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

    public Terreno(double precio, String localidad) throws IllegalArgumentException, NullPointerException {
        super(precio, localidad);
    }

    @Override
    public void calcularPrecioConIVA() {
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String representar() {
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
