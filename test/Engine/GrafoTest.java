package Engine;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    @Test
    void testeZeroRotas(){
        List<Route> rotas = new ArrayList<>();
        List<Obstaculo> obstaculos = new ArrayList<>();

        Exception exception =  assertThrows(IllegalArgumentException.class, () -> {
            new Grafo(rotas, obstaculos);
        });
        assertEquals("Grafo:iv", exception.getMessage());
    }

}