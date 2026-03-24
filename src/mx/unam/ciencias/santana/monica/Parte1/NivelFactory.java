package mx.unam.ciencias.santana.monica.Parte1;

public class NivelFactory {
    public static Nivel crearNivel(int nivelSeleccionado, int tamano){
        switch (nivelSeleccionado) {
            case 1:
                return new NivelFacil(tamano);
            case 2:
                return new NivelMedio(tamano); 
            case 3:
                return new NivelDificil(tamano); 
            default:
                System.out.println("Selección no válida. Se seleccionará nivel fácil por defecto.");
                return new NivelFacil(tamano);
        }
    }
    
}
