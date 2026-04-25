package Engine;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    @Test
    void testeNumeroValidoRotas(){
        Route rota1 = new Route(List.of(
                new Ponto(0,0), new Ponto(1,1), new Ponto(3,2), new Ponto(3,5)
        ));

        Route rota2 = new Route(List.of(
                new Ponto(9,1), new Ponto(5,1), new Ponto(3,2),
                new Ponto(2,3), new Ponto(0,4), new Ponto(3,5)
        ));
        List<Route> rotas = List.of(rota1, rota2);
        List<Obstaculo> obstaculos = new ArrayList<>();
        assertDoesNotThrow(()->{
            new Grafo(rotas,obstaculos);
        });
    }


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