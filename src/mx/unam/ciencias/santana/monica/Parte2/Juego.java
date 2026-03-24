package mx.unam.ciencias.santana.monica.Parte2;

import java.util.List;
import javax.swing.JOptionPane;

public class Juego {
    private static Juego instance = null;
    private Tablero tablero;
    private Tableropista tableropista;
    private int vidas;
    private int pistas;

    private Juego(int tamano) {
        this.tablero = new Tablero(tamano);
        this.tableropista = new Tableropista(tamano);
        this.vidas = 3;
        this.pistas = 3;
    }
    public void decrementarVidas(){
        vidas--;
    }
    public void decrementarPistas(){
        pistas--;
    }
    public static Juego obtenerInstancia(int tamano) {
        if (instance == null) {
            instance = new Juego(tamano);
        }
        return instance;
    }

    public void jugarEnCelda(int fila, int columna, char marca) {
        if (tablero.getCasilla(fila, columna).getEstado() == marca) {
            tableropista.marcarCasilla(fila, columna, marca);
        } else {
            vidas--;
            if (vidas == 0) {
                JOptionPane.showMessageDialog(null, "¡Te quedaste sin vidas! Fin del juego.");
            }
        }
    }

    public char darPista(int fila, int columna) {
        char bien = tablero.getCasilla(fila, columna).getEstado();
        return bien;
    }

    public boolean estaCompleto() {
        return tableropista.estaCompleto();
    }

    public void reset() {
        this.vidas = 3;
        this.pistas = 3;
    }

    public Tableropista getTableropista() {
        return tableropista;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPistas() {
        return pistas;
    }

    public void mostrarEstadoJuego() {
        List<List<Integer>> pistasFilas = tablero.getPistasFilas();
        System.out.println("Vidas restantes: " + vidas);
        System.out.println("Pistas restantes: " + pistas);
        System.out.println("Pistas de filas:");
        for (List<Integer> fila : pistasFilas) {
            System.out.println(fila);
        }

        System.out.println("Tablero juego:");
        for (int i = 0; i < tableropista.getTamano(); i++) {
            for (int j = 0; j < tableropista.getTamano(); j++) {
                System.out.print(tableropista.getCasilla(i, j).getEstado() + " ");
            }
            System.out.println();
        }
        System.out.println("Pistas de columnas:");
        for (List<Integer> columna : tablero.getPistasColumnas()) {
            System.out.println(columna);
        }
    }
}
