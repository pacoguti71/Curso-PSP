package utilidaddes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class AnalizadorHTTP_chrome extends JFrame {

    private JTextField campoURL;
    private JTextArea areaResultado;

    public AnalizadorHTTP_chrome() {
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
                // Construir la petición HTTP con cabeceras tipo Chrome
                String lineaInicio = "GET " + path + " HTTP/1.1";
                String cabeceras
                        = "Host: " + host + (puerto != 80 ? ":" + puerto : "") + "\r\n"
                        + "Connection: keep-alive\r\n"
                        + "Upgrade-Insecure-Requests: 1\r\n"
                        + "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                        + "(KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36\r\n"
                        + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8\r\n"
                        + "Accept-Encoding: gzip, deflate, br\r\n"
                        + "Accept-Language: es-ES,es;q=0.9,en;q=0.8\r\n";

                String peticionCompleta = lineaInicio + "\r\n" + cabeceras + "\r\n";

                // Mostrar la petición completa en texto normal
                areaResultado.append("===== PETICIÓN ENVIADA (TEXTO NORMAL) =====\n");
                areaResultado.append(lineaInicio + "\n");
                areaResultado.append(cabeceras + "\n");
                areaResultado.append("======== FIN PETICION ========\n");

                // Enviar petición (con codificación ISO-8859-1)
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "ISO-8859-1"));
                out.write(peticionCompleta);
                out.flush();

                // Leer respuesta cruda desde socket
                InputStream in = socket.getInputStream();
                ByteArrayOutputStream raw = new ByteArrayOutputStream();
                int b;
                while ((b = in.read()) != -1) {
                    raw.write(b);
                }
                String todoJunto = raw.toString("ISO-8859-1");

                // Mostrar la respuesta completa recibida
                areaResultado.append("\n===== RESPUESTA RECIBIDA DEL SOCKET =====\n");
                areaResultado.append(todoJunto);

                // También mostrar en consola
                System.out.println(todoJunto);

            } catch (IOException ex) {
                areaResultado.append("❌ Error de conexión: " + ex.getMessage());
            }

        } catch (MalformedURLException ex) {
            areaResultado.setText("❌ URL no válida: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnalizadorHTTP_chrome().setVisible(true));
    }
}
