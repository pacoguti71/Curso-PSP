package ejecutar;

import clases.Cliente;

public class EjecutarCliente {

    public static void main(String[] args) {
        int puertoServidor = 6000;
        String host = "localhost";

        Cliente cliente = new Cliente(host, puertoServidor);
        cliente.start();

    } // Fin main

} // Fin class
