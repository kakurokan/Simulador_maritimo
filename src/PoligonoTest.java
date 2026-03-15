import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PoligonoTest {

    @Test
    void getLados() {
        Ponto p1 = new Ponto(0, 0);
        Ponto p2 = new Ponto(0, 4);
        Ponto p3 = new Ponto(4, 0);

        Poligono poligono = new Poligono(new Ponto[]{
                p1,
                p2,
                p3
        });

        SegmentoReta[] lados = poligono.getLados();

        assertNotNull(lados);
        assertEquals(3, lados.length);

        // Verifica o Lado 0 (Aresta entre o Ponto 1 e Ponto 2)
        assertEquals(p1, lados[0].getA());
        assertEquals(p2, lados[0].getB());

        // Verifica o Lado 1 (Aresta entre o Ponto 2 e Ponto 3)
        assertEquals(p2, lados[1].getA());
        assertEquals(p3, lados[1].getB());

        // Verifica o Lado 2 (entre o Ponto 3 e o Ponto 1)
        assertEquals(p3, lados[2].getA());
        assertEquals(p1, lados[2].getB());
    }
}