package logica;

import java.util.Arrays;

public class Juego {

    private static final String[] PALABRAS = {"JAVA", "SOCKET", "HILO", "SERVIDOR", "CLIENTE"};

    private final String palabraSecreta;
    private char[] progreso;
    private int intentos = 6;
    private boolean juegoTerminado;

    public Juego() {
        this.palabraSecreta = PALABRAS[(int) (Math.random() * PALABRAS.length)];
        this.progreso = new char[palabraSecreta.length()];
        Arrays.fill(progreso, '_');
    }

    public String intentarLetra(char letra) {
        boolean acierto = false;

        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra && progreso[i] == '_') {
                progreso[i] = letra;
                acierto = true;
            }
        }

        if (!acierto) {
            intentos--;
        }

        if (palabraSecreta.equals(String.valueOf(progreso))) {
            juegoTerminado = true;
            return "¡Ganaste! La palabra era: " + palabraSecreta;
        }

        if (intentos == 0) {
            juegoTerminado = true;
            return "Perdiste. La palabra era: " + palabraSecreta;
        }

        return (acierto ? "¡Acertaste! " : "Fallaste.")
                + " Progreso: " + String.valueOf(progreso)
                + " | Intentos restantes: " + intentos;

    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

} // Fin class
