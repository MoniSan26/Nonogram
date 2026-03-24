package mx.unam.ciencias.santana.monica.Parte1;
import java.util.*;

public class Tablero {
    private Casilla[][] tablero;
    private int tamano;
    private List<List<Integer>> pistasFilas;
    private List<List<Integer>> pistasColumnas;

    public Tablero(int tamano) {
        this.tamano = tamano;
        this.tablero = new Casilla[tamano][tamano];
        this.pistasFilas = new ArrayList<>();
        this.pistasColumnas = new ArrayList<>();
        inicializarTablero();
    }

    private void inicializarTablero() {
        Random rand = new Random();
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                tablero[i][j] = new Casilla();
                tablero[i][j].setEstado(rand.nextBoolean() ? 'A' : 'X');
            }
        }
        generarPistas();
    }

    private void generarPistas() {
        for (int i = 0; i < tamano; i++) {
            pistasFilas.add(Pista.generarPista(getFila(i)));
            pistasColumnas.add(Pista.generarPista(getColumna(i)));
        }
    }

    private char[] getFila(int fila) {
        char[] row = new char[tamano];
        for (int i = 0; i < tamano; i++) {
            row[i] = tablero[fila][i].getEstado();
        }
        return row;
    }

    private char[] getColumna(int col) {
        char[] column = new char[tamano];
        for (int i = 0; i < tamano; i++) {
            column[i] = tablero[i][col].getEstado();
        }
        return column;
    }

    public Casilla getCasilla(int fila, int columna) {
        return tablero[fila][columna];
    }

    public void marcarCasilla(int fila, int columna, char estado) {
        tablero[fila][columna].setEstado(estado);
    }

    public List<List<Integer>> getPistasFilas() {
        return pistasFilas;
    }

    public List<List<Integer>> getPistasColumnas() {
        return pistasColumnas;
    }

    public int getTamano() {
        return tamano;
    }

    public boolean estaCompleto() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (tablero[i][j].getEstado() == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
