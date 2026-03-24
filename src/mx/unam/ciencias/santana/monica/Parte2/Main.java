package mx.unam.ciencias.santana.monica.Parte2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {
    private JPanel gridPanel;
    private JPanel controlPanel;
    private JPanel mainPanel;
    private JPanel topNumbersPanel;
    private JPanel leftNumbersPanel;
    private JToggleButton[][] cells;
    private JLabel vidasLabel;
    private JLabel pistasLabel;
    private int GRID_SIZE = 10;
    private final int CELL_SIZE = 30;
    private final Color FILLED_COLOR = Color.RED; 
    private final Color MARKED_COLOR = Color.GREEN; 
    private final Color EMPTY_COLOR = Color.WHITE;

    private Juego juego; 
    private char modoMarcado = 'A'; 

    public Main(int gridSize, int nivelSeleccionado) {
        this.GRID_SIZE = gridSize;
        this.juego = Juego.obtenerInstancia(gridSize); 
        Nivel nivel = NivelFactory.crearNivel(nivelSeleccionado, gridSize);
        nivel.iniciarJuego(); 
        setTitle("Nonogram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        pack();
        setLocationRelativeTo(null);
        juego.mostrarEstadoJuego();
    }

    private void initializeComponents() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        createGridWithNumbers();
        createControlPanel();

        mainPanel.add(topNumbersPanel, BorderLayout.NORTH);
        mainPanel.add(leftNumbersPanel, BorderLayout.WEST);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void createGridWithNumbers() {
        List<List<Integer>> pistasFilas = juego.getTablero().getPistasFilas();
        List<List<Integer>> pistasColumnas = juego.getTablero().getPistasColumnas();
    
        topNumbersPanel = new JPanel();
        topNumbersPanel.setLayout(new BoxLayout(topNumbersPanel, BoxLayout.X_AXIS));
    
        for (int i = 0; i < GRID_SIZE; i++) {
            List<Integer> pistaColumna = pistasColumnas.get(i);
            StringBuilder pistaStr = new StringBuilder();
            for (Integer num : pistaColumna) {
                pistaStr.append(num).append(" ");
            }
    
            JLabel label = new JLabel(pistaStr.toString().trim(), SwingConstants.CENTER);
            
            label.setPreferredSize(new Dimension(60, 20)); 
            label.setMaximumSize(new Dimension(60, 20));
            
            topNumbersPanel.add(label);
            topNumbersPanel.add(Box.createHorizontalStrut(10)); 
        }
    
        leftNumbersPanel = new JPanel(new GridLayout(GRID_SIZE, 1));
        for (int i = 0; i < GRID_SIZE; i++) {
            List<Integer> pistaFila = pistasFilas.get(i);
            StringBuilder pistaStr = new StringBuilder();
            for (Integer num : pistaFila) {
                pistaStr.append(num).append(" ");
            }
            JLabel label = new JLabel(pistaStr.toString().trim(), SwingConstants.RIGHT);
            label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            leftNumbersPanel.add(label);
        }
    
        gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        cells = new JToggleButton[GRID_SIZE][GRID_SIZE];
    
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = createCell(row, col);
                gridPanel.add(cells[row][col]);
            }
        }
    }
    

    private JToggleButton createCell(int row, int col) {
        JToggleButton cell = new JToggleButton();
        cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        cell.setBackground(EMPTY_COLOR);
        cell.setBorderPainted(true);
        cell.setContentAreaFilled(true);
        cell.setOpaque(true);
    
        cell.addActionListener(e -> {
            if (juego.getVidas() <= 0) {
                return; 
            }
    
            char estadoReal = juego.getTablero().getCasilla(row, col).getEstado();
    
            if (modoMarcado == 'A') {
                if (estadoReal == 'A') {
                    cell.setBackground(MARKED_COLOR); 
                    juego.getTablero().marcarCasilla(row, col, 'A'); 
                } else {
                    cell.setBackground(FILLED_COLOR); 
                    juego.decrementarVidas(); 
                }
            } else if (modoMarcado == 'X') {
                if (estadoReal == 'X') {
                    cell.setBackground(FILLED_COLOR); 
                    juego.getTablero().marcarCasilla(row, col, 'X'); 
                } else {
                    cell.setBackground(MARKED_COLOR); 
                    juego.decrementarVidas(); 
                }
            }
    
            actualizarVidasYPistas();
            verificarFinDelJuego();
            verificarVictoria();
        });
    
        return cell;
    }
    
    
    private void verificarFinDelJuego() {
        if (juego.getVidas() <= 0) {
            JOptionPane.showMessageDialog(null, "¡Has perdido! Te quedaste sin vidas.");
            bloquearTablero(); 
        }
    }
    
    private boolean verificarInterfazSincronizada() {
        boolean todoMarcado = true;
    
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                char estadoReal = juego.getTablero().getCasilla(row, col).getEstado(); 
                Color colorActual = cells[row][col].getBackground(); 
    
                if (estadoReal == 'A' && !colorActual.equals(MARKED_COLOR)) {
                    todoMarcado = false;
                } else if (estadoReal == 'X' && !colorActual.equals(FILLED_COLOR)) {
                    todoMarcado = false;
                } else if (estadoReal == ' ' && !colorActual.equals(EMPTY_COLOR)) {
                    todoMarcado = false;
                }
            }
        }
    
        return todoMarcado;
    }
    

    private void verificarVictoria() {
        if (verificarInterfazSincronizada()) { 
            JOptionPane.showMessageDialog(null, "¡Felicidades! Has ganado el juego.");
            bloquearTablero(); 
        }
    }
    
    private void bloquearTablero() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setEnabled(false); 
            }
        }
    }
    
    
    private void createControlPanel() {
        controlPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        JButton pistaButton = new JButton("Dar Pista");
        JButton marcarAButton = new JButton("Marcar A");
        JButton marcarXButton = new JButton("Marcar X");

        vidasLabel = new JLabel("Vidas: " + juego.getVidas());
        pistasLabel = new JLabel("Pistas: " + juego.getPistas());

        resetButton.addActionListener(e -> resetGrid());

        pistaButton.addActionListener(e -> {
            // Verifica si hay pistas disponibles
            if (juego.getPistas() > 0) {
                String filaStr = JOptionPane.showInputDialog("Ingresa la fila para la pista:");
                String colStr = JOptionPane.showInputDialog("Ingresa la columna para la pista:");
        
                try {
                    int fila = Integer.parseInt(filaStr);
                    int col = Integer.parseInt(colStr);
        
                    if (fila >= 0 && fila < GRID_SIZE && col >= 0 && col < GRID_SIZE) {
                        char estado = juego.darPista(fila, col); 
                        if (estado == 'A') {
                            cells[fila][col].setBackground(MARKED_COLOR); 
                        } else if (estado == 'X') {
                            cells[fila][col].setBackground(FILLED_COLOR); 
                        }
                        juego.decrementarPistas();
                        actualizarVidasYPistas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Fila o columna fuera de rango.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida.");
                }
            } else {
                // Si no hay pistas disponibles
                JOptionPane.showMessageDialog(null, "Ya no tienes pistas disponibles.");
            }
        });
        

        marcarAButton.addActionListener(e -> modoMarcado = 'A');
        marcarXButton.addActionListener(e -> modoMarcado = 'X');

        controlPanel.add(vidasLabel);
        controlPanel.add(pistasLabel);
        controlPanel.add(resetButton);
        controlPanel.add(pistaButton);
        controlPanel.add(marcarAButton);
        controlPanel.add(marcarXButton);
    }

    private void resetGrid() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setSelected(false);
                cells[row][col].setBackground(EMPTY_COLOR);
            }
        }
        juego.reset(); // Resetea el juego
        actualizarVidasYPistas();
    }

    private void actualizarVidasYPistas() {
        vidasLabel.setText("Vidas: " + Math.max(juego.getVidas(), 0)); 
        pistasLabel.setText("Pistas: " + juego.getPistas());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] options = {"Fácil", "Medio", "Difícil"};
            String level = (String) JOptionPane.showInputDialog(null, "Selecciona el nivel de dificultad:",
                    "Seleccionar Nivel", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            int nivelSeleccionado;
            int gridSize;
            switch (level) {
                case "Fácil":
                    nivelSeleccionado = 1;
                    gridSize = 5;
                    break;
                case "Medio":
                    nivelSeleccionado = 2;
                    gridSize = 10;
                    break;
                case "Difícil":
                    nivelSeleccionado = 3;
                    gridSize = 15;
                    break;
                default:
                    nivelSeleccionado = 1;
                    gridSize = 5;
                    break;
            }

            Main game = new Main(gridSize, nivelSeleccionado);
            game.setVisible(true);
        });
    }
}

