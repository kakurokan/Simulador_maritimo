package Engine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TempestadeTest {

    @Test
    void intersect() {
        Route rota = new Route(List.of(
                new Ponto(0, 0), new Ponto(1, 1), new Ponto(3, 2), new Ponto(3, 5)
        ));
        Tempestade tempestade = new Tempestade(new Circulo(new Ponto(3, 2), 2));

        List<Ponto> pontosEsperados = List.of(
                new Ponto(1.211, 1.105), new Ponto(3, 4)
        );

        assertEquals(pontosEsperados, tempestade.intersect(rota));
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

        assertEquals(areaOriginal.getCentro(), areaRetornada.getCentro());
        assertEquals(areaOriginal.getRaio(), areaRetornada.getRaio());
    }
}