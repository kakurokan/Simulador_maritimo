import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CirculoTest {

    @Test
    void getCentro() {
        assertEquals(new Ponto(3, 4), new Circulo(new Ponto(3, 4), 5).getCentro());
        assertEquals(new Ponto(-12, 4.5), new Circulo(new Ponto(-12, 4.5), 5).getCentro());
        assertEquals(new Ponto(0, 0), new Circulo(new Ponto(0, 0), 2).getCentro());
    }

    @Test
    void getRaio() {
        assertEquals(5.0, new Circulo(new Ponto(3, 4), 5).getRaio());
        assertEquals(4.5, new Circulo(new Ponto(-12, 4.5), 4.5).getRaio());
        assertEquals(2.0, new Circulo(new Ponto(0, 0), 2).getRaio());
    }

    @Test
    void intersectRota() {
        Route rota = new Route(List.of(
                new Ponto(0, 2),
                new Ponto(10, 2)
        ));
        Circulo circulo = new Circulo(new Ponto(5, 2), 2);
        List<Ponto> objetivo = List.of(
                new Ponto(3, 2),
                new Ponto(7, 2)
        );
        assertEquals(objetivo, circulo.intersect(rota));
    }
}