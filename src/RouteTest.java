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
    void intersectSegmentoReta() {
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

    @Test
    void intersectFiguraGeometrica() {
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
        assertEquals(objetivo, rota.Intersect(quadrado));

        rota = new Route(List.of(
                new Ponto(0, 2),
                new Ponto(10, 2)
        ));
        Circulo circulo = new Circulo(new Ponto(5, 2), 2);
        objetivo = List.of(
                new Ponto(3, 2),
                new Ponto(7, 2)
        );
        assertEquals(objetivo, rota.Intersect(circulo));

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
        assertEquals(objetivo, rota.Intersect(triangulo));
    }
}