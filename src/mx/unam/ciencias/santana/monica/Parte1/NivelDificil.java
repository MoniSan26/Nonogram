package mx.unam.ciencias.santana.monica.Parte1;

public class NivelDificil implements Nivel {
    private int tamano;

    public NivelDificil(int tamano) {
        this.tamano = tamano;
    }

    @Override
    public void iniciarJuego() {
        Juego.obtenerInstancia(tamano).jugar();
    }
}
