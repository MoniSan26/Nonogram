package mx.unam.ciencias.santana.monica.Parte1;
public class NivelFacil implements Nivel {
    private int tamano;

    public NivelFacil(int tamano) {
        this.tamano = tamano;
    }

    @Override
    public void iniciarJuego() {
        Juego.obtenerInstancia(tamano).jugar();
    }
}
