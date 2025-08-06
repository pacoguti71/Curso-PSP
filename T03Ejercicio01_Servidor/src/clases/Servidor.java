package clases;

import java.net.Socket;

public class Servidor extends Thread {

    private final Socket socketCliente;

    public Servidor(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {

    }

} // Fin class
