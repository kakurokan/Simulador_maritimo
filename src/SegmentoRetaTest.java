import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoRetaTest {

    @Test
    void testToString() {
        assertEquals("sr((1.00,2.00); (2.00,2.00))", new SegmentoReta(new Ponto(2, 2), new Vetor(-1, 0)).toString());
        assertEquals("sr((3.00,3.00); (5.00,7.50))", new SegmentoReta(new Ponto(3, 3), new Vetor(2, 4.5)).toString());
    }

    @Test
    void intersectVetor() {
        Ponto expected = new Ponto(1.1, 1.28);
        Vetor v = new Vetor(3.22, 3.72);
        SegmentoReta sr = new SegmentoReta(new Ponto(3.4, -0.24), new Ponto(-2.78, 3.84));
        Ponto result = sr.intersect(v);
        assertEquals(expected.getX(), result.getX(), 0.01);
        assertEquals(expected.getY(), result.getY(), 0.01);

        v = new Vetor(1, 4);
        sr = new SegmentoReta(new Ponto(4, 4), new Ponto(6, 6));
        assertNull(sr.intersect(v));

        // Paralelo
        v = new Vetor(4, 0);
        sr = new SegmentoReta(new Ponto(0, 2), new Ponto(4, 2));
        assertNull(sr.intersect(v));

        // Colineares, mas à frente
        v = new Vetor(2, 2);
        sr = new SegmentoReta(new Ponto(4, 4), new Ponto(6, 6));
        assertNull(sr.intersect(v));

        //Colinear
        v = new Vetor(4, 4);
        sr = new SegmentoReta(new Ponto(2, 2), new Ponto(6, 6));
        result = sr.intersect(v);
        assertTrue(result.equals(new Ponto(4, 4)) || result.equals(new Ponto(2, 2)));
    }

    @Test
    void intersectSegmento() {

        // Interseção simples
        SegmentoReta sr1 = new SegmentoReta(new Ponto(0, 0), new Ponto(4, 4));
        SegmentoReta sr2 = new SegmentoReta(new Ponto(0, 4), new Ponto(4, 0));
        Ponto p1 = sr1.intersect(sr2);
        assertEquals(2.0, p1.getX(), Ponto.eps);
        assertEquals(2.0, p1.getY(), Ponto.eps);

        // Interseção na extremidade
        SegmentoReta sr3 = new SegmentoReta(new Ponto(0, 0), new Ponto(2, 0));
        SegmentoReta sr4 = new SegmentoReta(new Ponto(2, 0), new Ponto(2, 2));
        Ponto p2 = sr3.intersect(sr4);
        assertEquals(2.0, p2.getX(), Ponto.eps);
        assertEquals(0.0, p2.getY(), Ponto.eps);

        // Paralelos
        SegmentoReta sr5 = new SegmentoReta(new Ponto(0, 0), new Ponto(4, 0));
        SegmentoReta sr6 = new SegmentoReta(new Ponto(0, 2), new Ponto(4, 2));
        assertNull(sr5.intersect(sr6));

        // 4. Colineares COM sobreposição
        SegmentoReta sr7 = new SegmentoReta(new Ponto(0, 0), new Ponto(4, 0));
        SegmentoReta sr8 = new SegmentoReta(new Ponto(2, 0), new Ponto(6, 0));
        Ponto p3 = sr7.intersect(sr8);
        assertEquals(2.0, p3.getX(), Ponto.eps);
        assertEquals(0.0, p3.getY(), Ponto.eps);

        // 5. Colineares SEM sobreposição
        SegmentoReta sr9 = new SegmentoReta(new Ponto(0, 0), new Ponto(2, 0));
        SegmentoReta sr10 = new SegmentoReta(new Ponto(4, 0), new Ponto(6, 0));
        assertNull(sr9.intersect(sr10));
    }
}