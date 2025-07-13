package clases;

public class Simulador {

    public static void main(String[] args) throws InterruptedException {

        Panaderia panaderia = new Panaderia();
        Panadero panadero1 = new Panadero("Panadero 1", panaderia);

        panadero1.start();
        panadero1.join();
        System.out.println("Fabricación terminada.");

    } // Fin main

} // Fin class
