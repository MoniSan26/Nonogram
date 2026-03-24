package mx.unam.ciencias.santana.monica.Parte1;
import java.util.*;

public class Juego {
    private static Juego instance = null;
    private Tablero tablero;
    private Tableropista tableropista;
    private AutoCompletarObserver autoCompletarObserver;
    private int vidas;
    private int pistas;

    private Juego(int tamano) {
        this.tablero = new Tablero(tamano);
        this.tableropista= new Tableropista(tamano);
        this.autoCompletarObserver = new AutoCompletarObserver(tableropista,tablero);
        tableropista.agregarObservador(autoCompletarObserver);
        this.vidas = 3;
        this.pistas = 3;
    }

    public static Juego obtenerInstancia(int tamano) {
        if (instance == null) {
            instance = new Juego(tamano);
        }
        return instance;
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;
        boolean validInput1 = false;
        boolean validInput2 = false;
        boolean validInput3 = false; 
        boolean validInput4 = false; 
        boolean validInput5 = false; 
        boolean validInput6 = false; 
        int fila =0;
        int columna=0;

        while (!juegoTerminado) {
            mostrarEstadoJuego(); 
            validInput4 = false;
            while (!validInput4 ) {
                System.out.print("¿Quieres Pistas? (Marca S=si y N=no)");
                char pista = scanner.next().charAt(0);
                if (pista == 'S' || pista == 'N') {
                    validInput4 = true;
                    if(pistas!=0 && pista=='S'){
                        validInput5 = false;
                        while (!validInput5) {
                            System.out.print("Ingresa la fila (0 a " + (tablero.getTamano() - 1) + "): ");
                            try {
                                fila = scanner.nextInt();
                                if (fila >= 0 && fila < tablero.getTamano()) {
                                    validInput5 = true;  // Fila válida, salimos del bucle
                                } else {
                                    System.out.println("Fila fuera de rango. Debes ingresar un número entre 0 y " + (tablero.getTamano() - 1));
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida. Debes ingresar un número entre 0 y " + (tablero.getTamano() - 1));
                                scanner.next(); // Descartar la entrada incorrecta
                            }
                        }
            
                        validInput6 = false;
                        while (!validInput6) {
                            System.out.print("Ingresa la columna (0 a " + (tablero.getTamano() - 1) + "): ");
                            try {
                                columna = scanner.nextInt();
                                if (columna >= 0 && columna < tablero.getTamano()) {
                                    validInput6 = true;  // Columna válida, salimos del bucle
                                } else {
                                    System.out.println("Columna fuera de rango. Debes ingresar un número entre 0 y " + (tablero.getTamano() - 1));
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida. Debes ingresar un número entre 0 y " + (tablero.getTamano() - 1));
                                scanner.next(); // Descartar la entrada incorrecta
                            }
                        }
                        darPista(fila,columna);
                        pistas--;
                        mostrarEstadoJuego(); 
                    }else if(pistas == 0){
                        System.out.println("Ya no tienes pistas");
                    }
                } else {
                    System.out.println("Entrada inválida. Solo puedes marcar con 'S' o 'N'.");
                }
            }
            validInput1 = false;
            while (!validInput1) {
                System.out.print("Ingresa la fila (0 a " + (tablero.getTamano() - 1) + "): ");
                try {
                    fila = scanner.nextInt();
                    if (fila >= 0 && fila < tablero.getTamano()) {
                        validInput1 = true;  // Fila válida, salimos del bucle
                    } else {
                        System.out.println("Fila fuera de rango. Debes ingresar un número entre 0 y " + (tablero.getTamano() - 1));
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Debes ingresar un número entre 0 y " + (tablero.getTamano() - 1));
                    scanner.next(); // Descartar la entrada incorrecta
                }
            }

            validInput2 = false;
            while (!validInput2) {
                System.out.print("Ingresa la columna (0 a " + (tablero.getTamano() - 1) + "): ");
                try {
                    columna = scanner.nextInt();
                    if (columna >= 0 && columna < tablero.getTamano()) {
                        validInput2 = true;  // Columna válida, salimos del bucle
                    } else {
                        System.out.println("Columna fuera de rango. Debes ingresar un número entre 0 y " + (tablero.getTamano() - 1));
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Debes ingresar un número entre 0 y " + (tablero.getTamano() - 1));
                    scanner.next(); // Descartar la entrada incorrecta
                }
            }
            validInput3 = false;
            while (!validInput3) {
                System.out.print("Marca con 'A' (lleno) o 'X' (vacío): ");
                char marca = scanner.next().charAt(0);
                if (marca == 'A' || marca == 'X') {
                    validInput3 = true;  // Marca válida, salimos del bucle
                    // Aquí puedes continuar con la lógica de marcación
                    if (!esCoordenadaValida(fila, columna)) {
                        System.out.println("Coordenada fuera de rango. Intenta de nuevo.");
                        continue;
                    }

                    if (tablero.getCasilla(fila, columna).getEstado() == marca) {
                        System.out.println("Correcto!");
                        tableropista.marcarCasilla(fila, columna, marca);
                    } else {
                        System.out.println("Incorrecto! Pierdes una vida.");
                        vidas--;
                    }

                    // Verifica si el tablero está completo
                    if (tableropista.estaCompleto()) {
                        mostrarEstadoJuego(); 
                        System.out.println("¡Felicidades! Has completado el tablero.");
                        juegoTerminado = true;
                    }

                    // Verifica si se han agotado las vidas
                    if (vidas == 0) {
                        System.out.println("¡Te quedaste sin vidas! Fin del juego.");
                        juegoTerminado = true;
                    }

                } else {
                    System.out.println("Entrada inválida. Solo puedes marcar con 'A' o 'X'.");
                }
            }

        }
        scanner.close();
    }


    private void mostrarEstadoJuego() {
        List<List<Integer>> pistasFilas = tablero.getPistasFilas();
        System.out.println("Vidas restantes: " + vidas);
        System.out.println("Pistas restantes: " + pistas);
        System.out.println("Pistas de filas:");
        for (List<Integer> fila : pistasFilas) {
            System.out.println(fila);
        }

        System.out.println("Tablero juego:");
        for (int i = 0; i < tableropista.getTamano(); i++) {
            for (int j = 0; j < tableropista.getTamano(); j++) {
                System.out.print(tableropista.getCasilla(i, j).getEstado() + " ");
            }
            System.out.println();
        }
        System.out.println("Pistas de columnas:");
        for (List<Integer> columna : tablero.getPistasColumnas()) {
            System.out.println(columna);
        }
    }

    private boolean esCoordenadaValida(int fila, int columna) {
        return fila >= 0 && fila < tablero.getTamano() && columna >= 0 && columna < tablero.getTamano();
    }

    private void darPista(int fila,int columna){
        char bien = tablero.getCasilla(fila,columna).getEstado();
        tableropista.marcarCasilla(fila, columna, bien);    
    }
}
