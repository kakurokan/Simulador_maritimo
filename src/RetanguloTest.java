import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RetanguloTest {

    @Test
    void testExceptionTrapezio() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(4, 0),
                new Ponto(3, 2),
                new Ponto(1, 2)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Retangulo(pontos));
        assertEquals("Retangulo:iv", ex.getMessage());
    }

    @Test
    void testExceptionParalelogramoNaoRetangulo() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(4, 0),
                new Ponto(5, 2),
                new Ponto(1, 2)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Retangulo(pontos));
        assertEquals("Retangulo:iv", ex.getMessage());
    }

    @Test
    void testExceptionTamanhoIncorreto() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(2, 0),
                new Ponto(1, 2)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Retangulo(pontos));
        assertEquals("Retangulo:iv", ex.getMessage());
    }

    @Test
    void testSucessoRetanguloNormal() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(4, 0),
                new Ponto(4, 2),
                new Ponto(0, 2)
        };

        assertDoesNotThrow(() -> new Retangulo(pontos));
    }

    @Test
    void testSucessoQuadrado() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(2, 0),
                new Ponto(2, 2),
                new Ponto(0, 2)
        };

        assertDoesNotThrow(() -> new Retangulo(pontos));
    }

    @Test
    void testSucessoRetanguloInclinado() {
        Ponto[] pontos = {
                new Ponto(0, 1),
                new Ponto(1, 0),
                new Ponto(3, 2),
                new Ponto(2, 3)
        };

        assertDoesNotThrow(() -> new Retangulo(pontos));
    }
}