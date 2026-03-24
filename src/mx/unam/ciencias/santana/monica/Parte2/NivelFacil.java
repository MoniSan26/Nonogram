package mx.unam.ciencias.santana.monica.Parte2;
public class NivelFacil implements Nivel {
    private int tamano;

    public NivelFacil(int tamano) {
        this.tamano = tamano;
    }

    @Override
    public void iniciarJuego() {
        Juego.obtenerInstancia(tamano);
    }
}
