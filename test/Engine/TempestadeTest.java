package Engine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TempestadeTest {

    @Test
    void intersect_RotaCruzaTempestade_RetornaPontosDeIntersecao() {
        Route rota = new Route(List.of(
                new Ponto(0, 0), new Ponto(1, 1), new Ponto(3, 2), new Ponto(3, 5)
        ));
        Circulo area = new Circulo(new Ponto(3, 2), 2);
        Tempestade tempestade = new Tempestade(area);

        List<Ponto> pontosEsperados = List.of(
                new Ponto(1.211, 1.105), // Valor aproximado
                new Ponto(3, 4)          // Valor exato
        );

        List<Ponto> resultado = tempestade.intersect(rota);

        assertNotNull(resultado, "A interseção deveria retornar uma lista válida de pontos.");

        assertListasPontosIguais(pontosEsperados, resultado, 0.005);
    }

    @Test
    void intersect_RotaLongeDaTempestade_RetornaNullOuVazio() {
        Route rota = new Route(List.of(
                new Ponto(0, 0), new Ponto(1, 1)
        ));
        Tempestade tempestade = new Tempestade(new Circulo(new Ponto(10, 10), 2));

        List<Ponto> resultado = tempestade.intersect(rota);

        // Melhoria: Cobrir ambas as abordagens comuns de implementação
        assertTrue(resultado == null || resultado.isEmpty(),
                "Uma rota que passa longe da tempestade deveria retornar null ou uma lista vazia.");
    }

    private void assertListasPontosIguais(List<Ponto> esperado, List<Ponto> resultado, double delta) {
        assertEquals(esperado.size(), resultado.size(), "O número de pontos de interseção está incorreto.");

        for (int i = 0; i < esperado.size(); i++) {
            Ponto pontoEsperado = esperado.get(i);
            Ponto pontoResultado = resultado.get(i);

            assertEquals(pontoEsperado.getX(), pontoResultado.getX(), delta, "Coordenada X errada no ponto " + i);
            assertEquals(pontoEsperado.getY(), pontoResultado.getY(), delta, "Coordenada Y errada no ponto " + i);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 2.3, 4.4",
            "-2, 0, 5.5",
            "1.1, 2.2, 3.3"
    })
    void getArea_TempestadeInstanciada_RetornaAreaCircularCorreta(double x, double y, double raio) {
        Ponto centro = new Ponto(x, y);
        Circulo areaOriginal = new Circulo(centro, raio);
        Tempestade tempestade = new Tempestade(areaOriginal);

        Circulo areaRetornada = tempestade.getArea();

        assertNotNull(areaRetornada, "A área retornada pela tempestade não pode ser nula.");
        assertEquals(areaOriginal.getCentro(), areaRetornada.getCentro(), "O centro da área da tempestade foi alterado.");
        assertEquals(areaOriginal.getRaio(), areaRetornada.getRaio(), "O raio da área da tempestade foi alterado.");
    }
}