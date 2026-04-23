import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

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
    void construtorComArray_double() {
        double[] coords = {0, 0, 3, 4, 6, 8};

        Route rota = new Route(coords);

        assertEquals(10.0, rota.Comprimento(), 0.01);
    }

    @Test
    void testConstrutorPontosIguais() {
        double[] coords = {1.0, 1.0, 1.0, 1.0, 2.0, 2.0};
        Route rota = new Route(coords);

        assertEquals(1.414, rota.Comprimento(), 0.01);
    }

    @Test
    void construtor_Par() {
        double[] coords = {10.5};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(coords);
        });
        assertEquals("Rota:iv", exception.getMessage());
    }

    @Test
    void construtor_NaoPar() {
        double[] coords = {0, 0, 3}; // 3 elementos = Ímpar

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Route(coords);
        });

        assertEquals("Rota:iv", exception.getMessage());
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