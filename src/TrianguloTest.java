import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrianguloTest {

    @Test
    void testExceptionColinearHorizontal() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(1, 0),
                new Ponto(2, 0)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Triangulo(pontos));
        assertEquals("Triangulo:iv", ex.getMessage());
    }

    @Test
    void testExceptionColinearVertical() {
        Ponto[] pontos = {
                new Ponto(5, 1),
                new Ponto(5, 5),
                new Ponto(5, 10)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Triangulo(pontos));
        assertEquals("Triangulo:iv", ex.getMessage());
    }

    @Test
    void testExceptionColinearDiagonal() {
        Ponto[] pontos = {
                new Ponto(1, 1),
                new Ponto(2, 2),
                new Ponto(3, 3)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Triangulo(pontos));
        assertEquals("Triangulo:iv", ex.getMessage());
    }

    @Test
    void testExceptionTamanhoIncorreto() {
        Ponto[] quatroPontos = {
                new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1), new Ponto(0, 1)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Triangulo(quatroPontos));
        assertEquals("Triangulo:iv", ex.getMessage());
    }

    @Test
    void testExceptionColinearSemRepetirPontos() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(1, 1),
                new Ponto(2, 2)
        };

        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Triangulo(pontos));
        assertEquals("Triangulo:iv", ex.getMessage());
    }

    @Test
    void testSucessoTrianguloValido() {
        Ponto[] pontos = {
                new Ponto(0, 0),
                new Ponto(1, 0),
                new Ponto(0, 1)
        };

        assertDoesNotThrow(() -> new Triangulo(pontos));
    }
}