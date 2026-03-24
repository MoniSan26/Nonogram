package mx.unam.ciencias.santana.monica.Parte2;
public class Casilla {
    private char estado; 

    public Casilla() {
        this.estado = '-';
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public boolean esLleno() {
        return estado == 'A';
    }

    public boolean esVacio() {
        return estado == 'X';
    }
}