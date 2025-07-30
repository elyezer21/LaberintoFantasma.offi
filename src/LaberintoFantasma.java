import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class LaberintoFantasma extends JPanel implements KeyListener {

    private static final int TILE = 40;
    private static final int MARGEN = 50;
    private static final int MAX_NIVELES = 5;

    // Laberintos por nivel (0 = camino, 1 = pared, 9 = salida)
    private final int[][][] niveles = new int[][][]{
            {
                    {1,1,1,1,1,1,1,1,1,1},
                    {1,0,0,0,1,0,0,0,0,1},
                    {1,0,1,0,1,0,1,1,0,1},
                    {1,0,1,0,0,0,0,1,0,1},
                    {1,0,1,1,1,1,0,1,0,1},
                    {1,0,0,0,0,1,0,1,0,1},
                    {1,1,1,1,0,1,0,1,0,1},
                    {1,0,0,1,0,0,0,0,0,1},
                    {1,0,1,1,1,1,1,1,9,1},
                    {1,1,1,1,1,1,1,1,1,1},
            },
            {
                    {1,1,1,1,1,1,1,1,1,1},
                    {1,0,1,0,0,0,1,0,0,1},
                    {1,0,1,1,1,0,1,1,0,1},
                    {1,0,0,0,1,0,0,1,0,1},
                    {1,1,1,0,1,1,0,1,0,1},
                    {1,0,0,0,0,0,0,1,0,1},
                    {1,0,1,1,1,1,1,1,0,1},
                    {1,0,0,0,0,0,0,0,0,1},
                    {1,0,1,1,1,1,1,1,1,1},
                    {1,9,1,1,1,1,1,1,1,1},
            },
            {
                    {1,1,1,1,1,1,1,1,0,9},
                    {1,0,1,0,0,0,1,0,0,1},
                    {1,0,1,1,1,0,1,1,0,1},
                    {1,0,0,0,1,0,0,1,0,1},
                    {1,1,1,0,1,1,0,1,0,1},
                    {1,0,0,0,0,0,0,1,0,1},
                    {1,0,1,1,1,1,0,1,0,1},
                    {1,0,1,0,0,0,0,0,0,1},
                    {1,0,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1},
            },
            {
                    {1,1,1,1,1,1,1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,1},
                    {1,0,1,1,1,1,1,1,0,1},
                    {1,0,1,0,0,0,0,1,0,1},
                    {1,0,1,0,1,9,0,1,0,1},
                    {1,0,1,0,0,0,0,1,0,1},
                    {1,0,1,1,0,0,0,1,0,1},
                    {1,0,0,1,0,0,0,1,0,1},
                    {1,1,0,0,0,0,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1},
            },
            {
                    {1,1,1,1,1,1,1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,1},
                    {1,0,1,1,1,1,1,1,0,1},
                    {1,0,1,0,0,0,0,1,0,1},
                    {1,0,1,0,1,1,0,1,0,1},
                    {1,0,1,0,0,1,0,1,0,1},
                    {1,0,1,1,0,1,0,1,0,1},
                    {1,0,0,1,0,0,0,1,1,1},
                    {1,1,0,0,0,0,0,0,9,1},
                    {1,1,1,1,1,1,1,1,1,1},
            }
    };

    // Estado del juego
    private int nivelActual = 0;
    private int[][] maze = niveles[nivelActual];
    private int px = 1, py = 1;
    private Color colorJugador = Color.RED;

    private int segundos = 0;
    private Timer timer;

    // Parte principal del juego
    public LaberintoFantasma() {
        setPreferredSize(new Dimension(maze[0].length * TILE, maze.length * TILE + MARGEN));
        setFocusable(true);
        addKeyListener(this);

        iniciarTemporizador();
        elegirColor();
    }

    private void elegirColor() {
        Color nuevoColor = JColorChooser.showDialog(this, "Elige el color del jugador", colorJugador);
        if (nuevoColor != null) {
            colorJugador = nuevoColor;
        }
    }


    private void iniciarTemporizador() {
        if (timer != null) timer.cancel();
        timer = new Timer();
        segundos = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                segundos++;
                repaint();
            }
        }, 1000, 1000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(15, 15, 15));

        // Crea laberinto
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                switch (maze[y][x]) {
                    case 1 -> g.setColor(new Color(70, 70, 70));     // Color Pared
                    case 9 -> g.setColor(new Color(0, 120, 0));      // Color Salida
                    default -> g.setColor(Color.DARK_GRAY);          // Color Camino
                }
                g.fillRect(x * TILE, y * TILE + MARGEN, TILE, TILE);
                g.setColor(Color.GRAY);
                g.drawRect(x * TILE, y * TILE + MARGEN, TILE, TILE);
            }
        }

        // Crea jugador
        g.setColor(colorJugador);
        g.fillOval(px * TILE, py * TILE + MARGEN, TILE, TILE);

        // Crea Temporizador
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Tiempo: " + segundos + " s", 10, 25);
        g.drawString("Nivel: " + (nivelActual + 1) + "/" + MAX_NIVELES, 130, 25);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int nextX = px;
        int nextY = py;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> nextY--;
            case KeyEvent.VK_DOWN -> nextY++;
            case KeyEvent.VK_LEFT -> nextX--;
            case KeyEvent.VK_RIGHT -> nextX++;
        }

        if (esMovimientoValido(nextX, nextY)) {
            px = nextX;
            py = nextY;
            repaint();

            if (maze[py][px] == 9) {
                avanzarNivel();
            }
        }
    }

    private boolean esMovimientoValido(int x, int y) {
        return y >= 0 && y < maze.length &&
                x >= 0 && x < maze[0].length &&
                maze[y][x] != 1;
    }

    private void avanzarNivel() {
        nivelActual++;
        if (nivelActual >= niveles.length) {
            timer.cancel();
            SwingUtilities.getWindowAncestor(this).dispose();
            new PantallaTerror(); // El jumpscare final
        } else {
            maze = niveles[nivelActual];
            px = py = 1;
            iniciarTemporizador();
            repaint();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}