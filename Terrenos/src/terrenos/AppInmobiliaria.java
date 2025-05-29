/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package terrenos;

/**
 *
 * @author anaranjo
 */
public class AppInmobiliaria {
    public static void main(String[] args) {
        System.out.println("--- Pruebas con Terrenos ---");

        // 1. Terrenos con algún parámetro incorrecto o fuera de rango
        System.out.println("\n--- Pruebas con errores ---");

        // Precios incorrectos
        try {
            new Terreno(500.0, "Madrid", 1.0, 1.0, 0.10);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (precio bajo): " + e.getMessage());
        
        } catch (NullPointerException e) {
            System.err.println("Error al crear terreno (precio nulo): " + e.getMessage());
        }
        try {
            new Terreno(100_000_000.0, "Barcelona", 1.0, 1.0, 0.10);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (precio alto): " + e.getMessage());
        }

        // Localidad a null o cadena vacía
        try {
            new Terreno(100000.0, null, 1.0, 1.0, 0.10);
        } catch (NullPointerException e) {
            System.err.println("Error al crear terreno (localidad nula): " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (localidad vacía): " + e.getMessage());
        }
        try {
            new Terreno(100000.0, "", 1.0, 1.0, 0.10);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (localidad vacía): " + e.getMessage());
        }

        // Base y/o altura fuera de rango
        try {
            new Terreno(100000.0, "Valencia", 0.005, 1.0, 0.10);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (base baja): " + e.getMessage());
        }
        try {
            new Terreno(100000.0, "Sevilla", 15.0, 1.0, 0.10);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (base alta): " + e.getMessage());
        }
        try {
            new Terreno(100000.0, "Bilbao", 1.0, 0.001, 0.10);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (altura baja): " + e.getMessage());
        }
        try {
            new Terreno(100000.0, "Málaga", 1.0, 12.0, 0.10);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (altura alta): " + e.getMessage());
        }

        // IVA incorrecto
        try {
            new Terreno(100000.0, "Cádiz", 1.0, 1.0, -0.05);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (IVA negativo): " + e.getMessage());
        }
        try {
            new Terreno(100000.0, "Alicante", 1.0, 1.0, 0.25);
        } catch (IllegalArgumentException e) {
            System.err.println("Error al crear terreno (IVA alto): " + e.getMessage());
        }

        // 2. Terrenos con datos correctos, mostrando su información y representación
        System.out.println("\n--- Pruebas con datos correctos ---");

        try {
            Terreno terreno1 = new Terreno(150000.0, "Madrid", 6.2, 2.9, 0.21);
            System.out.println("\n--- Terreno 1 ---");
            System.out.println(terreno1);
            System.out.println("Precio con IVA: " + String.format("%.2f", terreno1.calcularPrecioConIVA()) + " euros");
            System.out.println("Representación:\n" + terreno1.representar());
        } catch (IllegalArgumentException e) {
            System.err.println("Error inesperado al crear terreno 1: " + e.getMessage());
        }

        try {
            Terreno terreno2 = new Terreno(50000.0, "Barcelona", 0.3, 3.41, 0.10);
            System.out.println("\n--- Terreno 2 ---");
            System.out.println(terreno2);
            System.out.println("Precio con IVA: " + String.format("%.2f", terreno2.calcularPrecioConIVA()) + " euros");
            System.out.println("Representación:\n" + terreno2.representar());
        } catch (IllegalArgumentException e) {
            System.err.println("Error inesperado al crear terreno 2: " + e.getMessage());
        }

        try {
            //Terreno terreno3 = new Terreno(900.0, "Sevilla", 6, 3, 0.04);
            Terreno terreno3 = new Terreno(900.0, "Sevilla", 0.02, 0.05, 0.04);
            System.out.println("\n--- Terreno 3 ---");
            System.out.println(terreno3);
            System.out.println("Precio con IVA: " + String.format("%.2f", terreno3.calcularPrecioConIVA()) + " euros");
            System.out.println("Representación:\n" + terreno3.representar());
        } catch (IllegalArgumentException e) {
            System.err.println("Error inesperado al crear terreno 3: " + e.getMessage());
        }

        try {
            Terreno terreno4 = new Terreno(5000000.0, "Valencia", 10.0, 10.0, 0.21);
            System.out.println("\n--- Terreno 4 (límites máximos) ---");
            System.out.println(terreno4);
            System.out.println("Precio con IVA: " + String.format("%.2f", terreno4.calcularPrecioConIVA()) + " euros");
            System.out.println("Representación:\n" + terreno4.representar());
        } catch (IllegalArgumentException e) {
            System.err.println("Error inesperado al crear terreno 4: " + e.getMessage());
        }

        try {
            Terreno terreno5 = new Terreno(99000000.0, "Zaragoza", 0.01, 0.01, 0.0);
            System.out.println("\n--- Terreno 5 (límites mínimos) ---");
            System.out.println(terreno5);
            System.out.println("Precio con IVA: " + String.format("%.2f", terreno5.calcularPrecioConIVA()) + " euros");
            System.out.println("Representación:\n" + terreno5.representar());
        } catch (IllegalArgumentException e) {
            System.err.println("Error inesperado al crear terreno 5: " + e.getMessage());
        }
}}
