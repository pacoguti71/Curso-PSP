package clases;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Servidor extends Thread {

    private final Socket socketCliente;

    public Servidor(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {

        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)) {

            PeticionWEB peticionWEB = new PeticionWEB(entrada);
            System.out.println(peticionWEB.getMetodo());
            System.out.println(peticionWEB.getUrl());
            System.out.println(peticionWEB.getVersion());

        } catch (Exception e) {
        }

    }

} // Fin class
