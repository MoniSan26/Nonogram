package mx.unam.ciencias.santana.monica.Parte1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecciona el nivel de dificultad (1 - Fácil, 2 - Medio, 3 - Difícil): ");
        int nivelSeleccionado = scanner.nextInt();

        int tamano = 0;
        switch (nivelSeleccionado) {
            case 1: 
                tamano = 5;
                break;
            case 2: 
                tamano = 10;
                break;
            case 3:  
                tamano = 15;
                break;
            default:
                System.out.println("Selección no válida. Se seleccionará nivel fácil por defecto.");
                tamano = 5;
        }

        Nivel nivel = NivelFactory.crearNivel(nivelSeleccionado, tamano);
        nivel.iniciarJuego();

        scanner.close();
    }
}
