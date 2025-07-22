package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente extends Thread {

    private String host;
    private int puerto;

    public Cliente(String host, int puertoServidor) {
        this.host = host;
        puerto = puertoServidor;
    }

    @Override
    public void run() {
        try (
                Socket socket = new Socket(host, puerto);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                Scanner teclado = new Scanner(System.in)) {
            String mensajeServidor;

            while ((mensajeServidor = entrada.readLine()) != null) {
                System.out.println("Servidor dice: " + mensajeServidor);
                if (mensajeServidor.contains("Introduce una letra:")) {
                    String letra = teclado.nextLine().toUpperCase();
                    salida.println(letra);
                }

            }

        } catch (IOException ex) {
            System.err.println("Error");
        }

    }

} // Fin class
