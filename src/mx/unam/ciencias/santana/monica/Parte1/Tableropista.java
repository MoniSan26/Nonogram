package mx.unam.ciencias.santana.monica.Parte1;
import java.util.ArrayList;
import java.util.List;

public class Tableropista implements Subject {
    private Casilla[][] tablero;
    private int tamano;
    private List<Observer> observadores;

    public Tableropista(int tamano) {
        this.tamano = tamano;
        this.tablero = new Casilla[tamano][tamano];
        this.observadores = new ArrayList<>();
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                tablero[i][j] = new Casilla();
                tablero[i][j].setEstado('-');
            }
        }
    }

    @Override
    public void agregarObservador(Observer observador) {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(Observer observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(int fila, int columna, char estado) {
        for (Observer observador : observadores) {
            observador.actualizar(fila, columna, estado);
        }
    }

    public Casilla getCasilla(int fila, int columna) {
        return tablero[fila][columna];
    }

    public void marcarCasilla(int fila, int columna, char estado) {
        tablero[fila][columna].setEstado(estado);
        notificarObservadores(fila, columna, estado);
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
