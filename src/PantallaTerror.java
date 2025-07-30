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

        URL imgUrl = getClass().getResource("terror.jpg");
        if (imgUrl == null) {
            System.err.println("No se encontró la imagen.");
            return;
        }

        JLabel fondo = new JLabel(new ImageIcon(imgUrl));
        fondo.setHorizontalAlignment(SwingConstants.CENTER);
        fondo.setVerticalAlignment(SwingConstants.CENTER);
        add(fondo);

        reproducirSonido("terror.wav");

        setVisible(true);

        // Se cierra después de 5 segundos
        new Timer(5000, e -> System.exit(0)).start();
    }

    private void reproducirSonido(String nombreArchivo) {
        try (AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(nombreArchivo))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}