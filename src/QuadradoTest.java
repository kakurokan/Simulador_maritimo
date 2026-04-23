import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuadradoTest {

    @Test
    void testExceptionNaoEhQuadradoMasEhRetangulo() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(4, 0),
                new Ponto(4, 2),
                new Ponto(0, 2)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Quadrado(pontos));
        assertEquals("Quadrado:iv", ex.getMessage());
    }

    @Test
    void testExceptionLosango() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(2, -1),
                new Ponto(4, 0),
                new Ponto(2, 1)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Quadrado(pontos));
        assertEquals("Quadrado:iv", ex.getMessage());
    }

    @Test
    void testExceptionTamanhoErrado() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(1, 0),
                new Ponto(0, 1)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Quadrado(pontos));
        assertEquals("Quadrado:iv", ex.getMessage());
    }

    @Test
    void testExceptionPontosColineares() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(1, 0),
                new Ponto(2, 0),
                new Ponto(3, 0)
        };

        assertThrows(IllegalArgumentException.class, () -> new Quadrado(pontos), "Quadrado:iv");
    }

    @Test
    void testSucessoQuadradoValido() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(2, 0),
                new Ponto(2, 2),
                new Ponto(0, 2)
        };

        assertDoesNotThrow(() -> new Quadrado(pontos));
    }

    @Test
    void testSucessoQuadradoRodado() {
        Ponto[] pontos = {
                new Ponto(1, 0),
                new Ponto(2, 1),
                new Ponto(1, 2),
                new Ponto(0, 1)
        };

        assertDoesNotThrow(() -> new Quadrado(pontos));
    }
}