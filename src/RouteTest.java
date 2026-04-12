import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void intersectPoligono() {
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

        rota = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(1, 1)
        ));
        triangulo = new Triangulo(new Ponto[]{
                new Ponto(5, 5),
                new Ponto(10, 5),
                new Ponto(7, 10)
        });
        assertNull(rota.Intersect(triangulo));
    }

    @Test
    void intersectCirculo() {
        Route rota = new Route(List.of(
                new Ponto(0, 2),
                new Ponto(10, 2)
        ));
        Circulo circulo = new Circulo(new Ponto(5, 2), 2);
        List<Ponto> objetivo = List.of(
                new Ponto(3, 2),
                new Ponto(7, 2)
        );
        assertEquals(objetivo, rota.Intersect(circulo));
    }

    @Test
    void getSegmento() {
        Ponto p1 = new Ponto(0, 0);
        Ponto p2 = new Ponto(2, 4);
        Ponto p3 = new Ponto(5, 1);

        List<Ponto> pontos = List.of(p1, p2, p3);
        Route rota = new Route(pontos);

        List<SegmentoReta> result = rota.getSegmentos();

        assertNotNull(result);

        assertEquals(2, result.size());

        assertEquals(p1, result.get(0).getA(), "A origem do primeiro segmento deve ser o Ponto 1.");
        assertEquals(p2, result.get(0).getB(), "O destino do primeiro segmento deve ser o Ponto 2.");

        assertEquals(p2, result.get(1).getA(), "A origem do segundo segmento deve ser o Ponto 2.");
        assertEquals(p3, result.get(1).getB(), "O destino do segundo segmento deve ser o Ponto 3.");
    }
}