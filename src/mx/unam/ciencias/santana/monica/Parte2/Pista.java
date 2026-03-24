package mx.unam.ciencias.santana.monica.Parte2;
import java.util.*;

public class Pista {

    public static List<Integer> generarPista(char[] linea) {
        List<Integer> pista = new ArrayList<>();
        int count = 0;
        
        for (char c : linea) {
            if (c == 'A') {
                count++;
            } else {
                if (count > 0) {
                    pista.add(count);
                    count = 0;
                }
            }
        }
        if (count > 0) {
            pista.add(count);
        }
        return pista;
    }
}
