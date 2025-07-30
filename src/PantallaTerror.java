import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class PantallaTerror extends JFrame {

    public PantallaTerror() {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        URL imgUrl = getClass().getResource("/terror.jpg");
        if (imgUrl == null) {
            System.err.println("No se encontró la imagen.");
            return;
        }

        JLabel fondo = new JLabel(new ImageIcon(imgUrl));
        fondo.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.setVerticalAlignment(SwingConstants.CENTER);
        add(fondo);

        reproducirSonido();

        setVisible(true);

        // Timer para cerrar imagen de terror
        new Timer(5000, e -> System.exit(0)).start();
    }

    private URL getResourceURL(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            throw new IllegalStateException("Recurso no encontrado: " + path);
        }
        return url;
    }
    private void reproducirSonido() {
        try (AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource("/terror.wav"))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception ex) {
            System.err.println("Error al reproducir sonido: " + ex.getMessage());
        }
    }
}
