package mx.unam.ciencias.santana.monica.Parte2;


public class Tableropista {
    private Casilla[][] tablero;
    private int tamano;

    public Tableropista(int tamano) {
        this.tamano = tamano;
        this.tablero = new Casilla[tamano][tamano];
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


    public Casilla getCasilla(int fila, int columna) {
        return tablero[fila][columna];
    }

    public void marcarCasilla(int fila, int columna, char estado) {
        tablero[fila][columna].setEstado(estado);
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

    public void reset() {
        inicializarTablero();
    }
}

    