package mx.unam.ciencias.santana.monica.Parte1;
public class NivelMedio implements Nivel {
    private int tamano;

    public NivelMedio(int tamano) {
        this.tamano = tamano;
    }

    @Override
    public void iniciarJuego() {
        Juego.obtenerInstancia(tamano).jugar();
    }
}
