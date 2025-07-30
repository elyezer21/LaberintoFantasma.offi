import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class Main extends JFrame {

    public Main() {
        setTitle("Laberinto Fantasma");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        LaberintoFantasma juego = new LaberintoFantasma();
        add(juego);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        juego.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PantallaInicio::new);
    }
}