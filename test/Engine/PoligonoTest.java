package Engine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PoligonoTest {

    @Test
    void construtor_ArrayVazio_LancaIllegalArgumentException() {
        Ponto[] vazio = {};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Poligono(vazio);
        });

        assertEquals("Engine.Poligono:iv", exception.getMessage());
    }

    @Test
    void construtor_UmPonto_LancaIllegalArgumentException() {
        Ponto[] umPonto = {new Ponto(5, 5)};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Poligono(umPonto);
        });

        assertEquals("Engine.Poligono:iv", exception.getMessage());
    }

    @Test
    void construtor_DoisPontos_LancaIllegalArgumentException() {
        Ponto[] doisPontos = {
                new Ponto(0, 0),
                new Ponto(1, 1)
        };

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Poligono(doisPontos);
        });

        assertEquals("Engine.Poligono:iv", exception.getMessage());
    }

    @Test
    void construtor_TresPontosValidos_NaoLancaExcecao() {
        Ponto[] tresPontos = {
                new Ponto(0, 0),
                new Ponto(1, 0),
                new Ponto(0, 1)
        };

        assertDoesNotThrow(() -> new Poligono(tresPontos));
    }

    @Test
    void intersectRota_RotaCruzaQuadrado_RetornaPontosDeIntersecao() {
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

        assertEquals(objetivo, quadrado.intersect(rota));
    }

    @Test
    void intersectRota_RotaCruzaTriangulo_RetornaPontosDeIntersecao() {
        Route rota = new Route(List.of(
                new Ponto(0, 2),
                new Ponto(3, 2),
                new Ponto(5, 0)
        ));
        Triangulo triangulo = new Triangulo(new Ponto[]{
                new Ponto(1, 1),
                new Ponto(3, 3),
                new Ponto(5, 1),
        });
        List<Ponto> objetivo = List.of(
                new Ponto(2, 2),
                new Ponto(4, 1)
        );

        assertEquals(objetivo, triangulo.intersect(rota));
    }

    @Test
    void intersectRota_RotaForaDoTriangulo_RetornaNull() {
        Route rota = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(1, 1)
        ));
        Triangulo triangulo = new Triangulo(new Ponto[]{
                new Ponto(5, 5),
                new Ponto(10, 5),
                new Ponto(7, 10)
        });

        assertNull(triangulo.intersect(rota));
    }
}