import org.junit.jupiter.api.Test;

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
}