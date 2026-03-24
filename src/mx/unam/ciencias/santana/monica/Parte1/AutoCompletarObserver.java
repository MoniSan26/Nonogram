package mx.unam.ciencias.santana.monica.Parte1;
public class AutoCompletarObserver implements Observer {
    private Tableropista tableroPista;  
    private Tablero tablero;  

    public AutoCompletarObserver(Tableropista tableroPista, Tablero tablero) {
        this.tableroPista = tableroPista;
        this.tablero = tablero;
    }

    @Override
    public void actualizar(int fila, int columna, char estado) {
        if (estado == 'A') {
            verificarYCompletarFila(fila);
            verificarYCompletarColumna(columna);
        }
    }

    private void verificarYCompletarFila(int fila) {
        boolean filaCompleta = true;

        for (int i = 0; i < tablero.getTamano(); i++) {
            char estado = tableroPista.getCasilla(fila, i).getEstado();
            char estadog = tablero.getCasilla(fila, i).getEstado();
            if (estadog == 'A' && estado != 'A') {
                filaCompleta = false;
                break;
            }
        }

        if (filaCompleta) {
            for (int i = 0; i < tableroPista.getTamano(); i++) {
                if (tableroPista.getCasilla(fila, i).getEstado() == '-') {
                    tableroPista.marcarCasilla(fila, i, 'X');
                }
            }
        }
    }

    private void verificarYCompletarColumna(int columna) {
        boolean columnaCompleta = true;

        for (int i = 0; i < tablero.getTamano(); i++) {
            char estado = tableroPista.getCasilla(i, columna).getEstado();
            char estadog = tablero.getCasilla(i, columna).getEstado();
            if (estadog == 'A' && estado != 'A') {
                columnaCompleta = false;
                break;
            }
        }

        if (columnaCompleta) {
            for (int i = 0; i < tableroPista.getTamano(); i++) {
                if (tableroPista.getCasilla(i, columna).getEstado() == '-') {
                    tableroPista.marcarCasilla(i, columna, 'X');
                }
            }
        }
    }
}
