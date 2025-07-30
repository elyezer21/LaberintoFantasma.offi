import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class PantallaInicio extends JFrame {

    public PantallaInicio() {
        setTitle("Presentación del Proyecto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel logosPanel = new JPanel(new BorderLayout());
        logosPanel.setBackground(Color.WHITE);

        ImageIcon iconUTP = new ImageIcon(getClass().getResource("/resources/logo_utp.png"));
        Image scaledUTP = iconUTP.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoUTP = new JLabel(new ImageIcon(scaledUTP));
        logoUTP.setHorizontalAlignment(SwingConstants.LEFT);
        logosPanel.add(logoUTP, BorderLayout.WEST);

        ImageIcon iconFISC = new ImageIcon(getClass().getResource("/resources/logo_fisc.png"));
        Image scaledFISC = iconFISC.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoFISC = new JLabel(new ImageIcon(scaledFISC));
        logoFISC.setHorizontalAlignment(SwingConstants.RIGHT);
        logosPanel.add(logoFISC, BorderLayout.EAST);

        add(logosPanel, BorderLayout.NORTH);

        JPanel datosPanel = new JPanel();
        datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));
        datosPanel.setBackground(Color.WHITE);

        Font tituloFont = new Font("Arial", Font.BOLD, 20);

        datosPanel.add(Box.createVerticalStrut(10));

        JLabel titulo1 = new JLabel("UNIVERSIDAD TECNOLÓGICA DE PANAMÁ");
        titulo1.setFont(tituloFont);
        titulo1.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(titulo1);

        JLabel titulo2 = new JLabel("FACULTAD DE INGENIERÍA DE SISTEMAS COMPUTACIONALES");
        titulo2.setFont(tituloFont);
        titulo2.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(titulo2);

        JLabel titulo3 = new JLabel("LICENCIATURA EN INGENIERÍA DE SOFTWARE");
        titulo3.setFont(tituloFont);
        titulo3.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(titulo3);

        datosPanel.add(Box.createVerticalStrut(25));

        JLabel grupo = new JLabel("GRUPO: 1SF121");
        grupo.setFont(tituloFont);
        grupo.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(grupo);

        datosPanel.add(Box.createVerticalStrut(25));
     // Encabezado de integrantes
        JLabel integrantesLabel = new JLabel("INTEGRANTES:");
        integrantesLabel.setFont(tituloFont);
        integrantesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(integrantesLabel);
     // Lista de integrantes
        String[] integrantes = {
                "Elyezer Portillo – 20-70-7338",
                "Bolivar Cedeño – 8-999-2068",
                "Yamar Camarena – 8-1000-2129"
        };
        for (String nombre : integrantes) {
            JLabel label = new JLabel(nombre);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            datosPanel.add(label);
        }

        datosPanel.add(Box.createVerticalStrut(25));

        JLabel profesorLabel = new JLabel("PROFESOR:");
        profesorLabel.setFont(tituloFont);
        profesorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(profesorLabel);

        JLabel profesorNombre = new JLabel("Rodrigo Yángüez");
        profesorNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(profesorNombre);

        datosPanel.add(Box.createVerticalStrut(25));

        JLabel fechaLabel = new JLabel("FECHA DE ENTREGA:");
        fechaLabel.setFont(tituloFont);
        fechaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(fechaLabel);

        JLabel fecha = new JLabel("30/07/2025 - I semestre 2025");
        fecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        datosPanel.add(fecha);

        add(datosPanel, BorderLayout.CENTER);
     // Botón para iniciar el juego o proyecto
        JButton iniciar = new JButton("Iniciar Juego");
        iniciar.setFont(new Font("Arial", Font.BOLD, 20));
        iniciar.addActionListener(e -> {
            dispose();
            new Main();
        });

        JPanel botonPanel = new JPanel();
        botonPanel.setBackground(Color.WHITE);
        botonPanel.add(iniciar);
        add(botonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    // Método auxiliar para obtener recursos con verificación
    private URL getResourceURL(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            throw new IllegalStateException("Recurso no encontrado: " + path);
        }
        return url;
    }
}
