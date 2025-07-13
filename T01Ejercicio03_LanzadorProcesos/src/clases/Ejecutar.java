package clases;

import java.io.IOException;

public class Ejecutar {

    public static void main(String[] args) throws IOException {

        Runtime.getRuntime().exec("calc.exe");

        Process p1 = new ProcessBuilder("calc.exe").start();

        ProcessBuilder p2 = new ProcessBuilder("calc.exe");
        p2.start();

        String rutaClases = System.getProperty("java.class.path");
        Process p3 = new ProcessBuilder("java", "-cp", rutaClases, "clases.Saludo").inheritIO().start();
    } // Fin main

} // Fin class
