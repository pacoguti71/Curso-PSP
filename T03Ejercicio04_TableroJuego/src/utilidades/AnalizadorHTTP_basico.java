package utilidaddes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class AnalizadorHTTP_basico extends JFrame {

    private JTextField campoURL;
    private JTextArea areaResultado;

    public AnalizadorHTTP_basico() {
        setTitle("Analizador HTTP (GET)");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        campoURL = new JTextField("");
        JButton botonEnviar = new JButton("Enviar petición");
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaResultado);

        // Layout
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(new JLabel("URL:"), BorderLayout.WEST);
        panelSuperior.add(campoURL, BorderLayout.CENTER);
        panelSuperior.add(botonEnviar, BorderLayout.EAST);

        getContentPane().add(panelSuperior, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);

        // Acción del botón
        botonEnviar.addActionListener((ActionEvent e) -> procesarPeticion());
    }

    private void procesarPeticion() {
        areaResultado.setText(""); // Limpiar salida

        try {
            String urlStr = campoURL.getText().trim();
            if (!urlStr.matches("^https?://.*")) {
                urlStr = "http://" + urlStr;
            }
            URL url = new URL(urlStr);
            String host = url.getHost();
            String path = url.getPath().isEmpty() ? "/" : url.getPath();
            int puerto = url.getPort() == -1 ? 80 : url.getPort();

            if (!url.getProtocol().equalsIgnoreCase("http")) {
                areaResultado.setText("⚠️ Solo se admite HTTP (no HTTPS) en esta versión.");
                return;
            }

            try (Socket socket = new Socket(host, puerto)) {
                // Construir petición
                String lineaInicio = "GET " + path + " HTTP/1.1";
                String cabeceras
                        = "Host: " + host + "\r\n"
                        + "User-Agent: AnalizadorHTTPSwing/1.0\r\n"
                        + "Connection: close\r\n";

                // Mostrar lo que se va a enviar
                areaResultado.append("======== PETICIÓN ENVIADA ========\n");
                areaResultado.append(lineaInicio + "\n");
                areaResultado.append(cabeceras + "\n");
                areaResultado.append("======== FIN PETICION ========\n");

                // Enviar petición
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                out.write(lineaInicio + "\r\n");
                out.write(cabeceras + "\r\n"); // línea en blanco
                out.flush();

                // Leer respuesta
                areaResultado.append("\n===== RESPUESTA DEL SERVIDOR =====\n");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String linea;
                boolean enCabeceras = true;
                while ((linea = in.readLine()) != null) {
                    if (linea.isEmpty() && enCabeceras) {
                        enCabeceras = false;
                        areaResultado.append("\n===== CUERPO =====\n");
                    }
                    areaResultado.append(linea + "\n");
                }

            } catch (IOException ex) {
                areaResultado.append("❌ Error de conexión: " + ex.getMessage());
            }

        } catch (MalformedURLException ex) {
            areaResultado.setText("❌ URL no válida: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnalizadorHTTP_basico().setVisible(true));
    }
}