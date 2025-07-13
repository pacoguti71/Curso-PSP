package clases;

import utilidades.Colores;



public class Panadero {
    
    private static final int MAX_PAN_DISPONIBLE=20;
    
    private static int panFabricado=0;
    
    public static void main(String[] args) {
        
        System.out.println(Colores.VERDE+"Iniciando la producción de pan...");
        while (puedoFabricar()) {
            fabricarPan();
        }
        
        System.out.println(Colores.VERDE+"Capacidad máxima ha sido alcanzada. Producción finalizada.");
        
    } // Fin main

    private static void fabricarPan() {
        panFabricado++;
        System.out.println("He fabricado una unidad de pan. Ahora hay "+panFabricado+" panes disponibles.");
    }

    private static boolean puedoFabricar() {
        return panFabricado<MAX_PAN_DISPONIBLE;
    }


} // Fin class