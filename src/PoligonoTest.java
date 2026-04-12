import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PoligonoTest {

    @Test
    void intersectRota() {
        Route rota = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(2, 2),
                new Ponto(2, 6)
        ));
        Quadrado quadrado = new Quadrado(new Ponto[]{
                new Ponto(1, 0),
                new Ponto(1, 3),
                new Ponto(4, 3),
                new Ponto(4, 0)
        });
        List<Ponto> objetivo = List.of(
                new Ponto(1, 1),
                new Ponto(2, 3)
        );
        assertEquals(objetivo, quadrado.intersect(rota));

        rota = new Route(List.of(
                new Ponto(0, 2),
                new Ponto(3, 2),
                new Ponto(5, 0)
        ));
        Triangulo triangulo = new Triangulo(new Ponto[]{
                new Ponto(1, 1),
                new Ponto(3, 3),
                new Ponto(5, 1),
        });
        objetivo = List.of(
                new Ponto(2, 2),
                new Ponto(4, 1)
        );
        assertEquals(objetivo, triangulo.intersect(rota));

        rota = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(1, 1)
        ));
        triangulo = new Triangulo(new Ponto[]{
                new Ponto(5, 5),
                new Ponto(10, 5),
                new Ponto(7, 10)
        });
        assertNull(triangulo.intersect(rota));
    }
}