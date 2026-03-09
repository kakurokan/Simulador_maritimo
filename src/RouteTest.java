import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RouteTest {

    @Test
    void comprimento() {
        List<Ponto> pontos = List.of(
                new Ponto(0, 1),
                new Ponto(1, 1),
                new Ponto(4, 4),
                new Ponto(4, 3)
        );
        assertEquals(6.24, new Route(pontos).Comprimento(), 0.01);

        pontos = List.of(
                new Ponto(4, 4),
                new Ponto(6, 6),
                new Ponto(7, 7)
        );
        assertEquals(4.24, new Route(pontos).Comprimento(), 0.01);

        pontos = List.of(
                new Ponto(4, 4),
                new Ponto(4, 4),
                new Ponto(6, 7)
        );
        assertEquals(3.61, new Route(pontos).Comprimento(), 0.01);
    }

    @Test
    void intersect() {
        List<Ponto> pontos = List.of(
                new Ponto(0, 1),
                new Ponto(1, 1),
                new Ponto(4, 4),
                new Ponto(4, 3)
        );
        SegmentoReta seg = new SegmentoReta(new Ponto(2, 1), new Ponto(2, 4));
        List<Ponto> esperado = List.of(new Ponto(2, 2));
        assertEquals(esperado, new Route(pontos).Intersect(seg));

        pontos = List.of(
                new Ponto(4, 4),
                new Ponto(6, 6),
                new Ponto(7, 7)
        );
        seg = new SegmentoReta(new Ponto(2, 2), new Ponto(4, 2));
        assertNull(new Route(pontos).Intersect(seg));
    }
}