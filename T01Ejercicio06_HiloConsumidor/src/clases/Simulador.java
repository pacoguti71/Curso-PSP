package clases;

public class Simulador {

    public static void main(String[] args) throws InterruptedException {

        Panaderia panaderia = new Panaderia();
        Panadero panadero1 = new Panadero("Panadero 1", panaderia);
        Panadero panadero2 = new Panadero("Panadero 2", panaderia);
        Panadero panadero3 = new Panadero("Panadero 3", panaderia);
        Cliente cliente = new Cliente("Cliente 1", 4, panaderia);

        panadero1.start();
        panadero2.start();
        panadero3.start();
        //Thread.sleep(3000);
        cliente.start();
        
        panadero1.join();
        panadero2.join();
        panadero3.join();
        cliente.join();
        
        System.out.println("Fabricación terminada.");

    } // Fin main

} // Fin class
