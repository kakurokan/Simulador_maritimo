package Engine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TempestadeTest {

    @Test
    void intersect() {
        Route rota = new Route(List.of(
                new Ponto(0,0), new Ponto(1,1), new Ponto(3,2), new Ponto(3,5)
        ));
        Tempestade tempestade = new Tempestade(new Circulo(new Ponto(3,2),2));

        List<Ponto> pontosEsperados = List.of(
                new Ponto(1.211,1.105), new Ponto(3,4)
        );

        assertEquals(pontosEsperados, tempestade.intersect(rota));
    }
}