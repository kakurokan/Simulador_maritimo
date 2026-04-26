package Engine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CirculoTest {

    @Test
    void construtor_RaioZero_LancaIllegalArgumentException() {
        Ponto centro = new Ponto(0, 0);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Circulo(centro, 0.0);
        });

        assertEquals("Engine.Circulo:iv", ex.getMessage());
    }

    @Test
    void construtor_RaioNegativo_LancaIllegalArgumentException() {
        Ponto centro = new Ponto(5, 5);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Circulo(centro, -1.0);
        });

        assertEquals("Engine.Circulo:iv", ex.getMessage());
    }

    @Test
    void construtor_RaioAbaixoDoEpsilon_LancaIllegalArgumentException() {
        Ponto centro = new Ponto(1, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            new Circulo(centro, 1e-10);
        }, "Um raio com valor positivo mas abaixo da tolerância mínima deve ser rejeitado.");
    }

    @Test
    void construtor_RaioValidoNoLimite_NaoLancaExcecao() {
        Ponto centro = new Ponto(0, 0);

        assertDoesNotThrow(() -> new Circulo(centro, Ponto.eps + 1e-12),
                "Um raio ligeiramente acima do epsilon deve ser aceite.");
    }

    @Test
    void getCentro_CirculoInstanciado_RetornaPontoCorreto() {
        assertEquals(new Ponto(3, 4), new Circulo(new Ponto(3, 4), 5).getCentro());
        assertEquals(new Ponto(-12, 4.5), new Circulo(new Ponto(-12, 4.5), 5).getCentro());
        assertEquals(new Ponto(0, 0), new Circulo(new Ponto(0, 0), 2).getCentro());
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, 5.0, 5.0",
            "-12, 4.5, 4.5, 4.5",
            "0, 0, 2.0, 2.0"
    })
    void getRaio_CirculoInstanciado_RetornaRaioCorreto(double x, double y, double raioInicial, double raioEsperado) {
        Circulo circulo = new Circulo(new Ponto(x, y), raioInicial);
        assertEquals(raioEsperado, circulo.getRaio());
    }

    @Test
    void setCentro_NovoPonto_AtualizaCentroDoCirculo() {
        Circulo circulo = new Circulo(new Ponto(0, 0), 5);
        Ponto novoCentro = new Ponto(5, 5);

        circulo.setCentro(novoCentro);

        assertEquals(novoCentro, circulo.getCentro(), "O centro do círculo deveria ter sido atualizado para o novo ponto.");
    }

    @Test
    void intersect_RotaCruzaCirculo_RetornaPontosDeIntersecao() {
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

    @Test
    void intersect_CirculosComSobreposicao_RetornaTrue() {
        Circulo a = new Circulo(new Ponto(0, 0), 5);
        Circulo b = new Circulo(new Ponto(6, 0), 4);

        assertTrue(a.intersect(b), "Círculos cuja distância entre centros é menor que a soma dos raios devem intersetar-se.");
    }

    @Test
    void intersect_CirculosAfastados_RetornaFalse() {
        Circulo a = new Circulo(new Ponto(0, 0), 5);
        Circulo c = new Circulo(new Ponto(15, 10), 3);

        assertFalse(a.intersect(c), "Círculos cuja distância entre centros é maior que a soma dos raios não devem intersetar-se.");
    }
}