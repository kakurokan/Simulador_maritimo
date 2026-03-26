import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RouteTest {

    @Test
    void comprimento() {
        List<Ponto> pontos = List.of(
                new Ponto(0, 1),
                new Ponto(1, 1),
                new Ponto(4, 4),
                new Ponto(4, 3)
        );
        assertEquals(6.24, new Route(pontos).Comprimento(), 0.01);

        pontos = List.of(
                new Ponto(4, 4),
                new Ponto(6, 6),
                new Ponto(7, 7)
        );
        assertEquals(4.24, new Route(pontos).Comprimento(), 0.01);

        pontos = List.of(
                new Ponto(4, 4),
                new Ponto(4, 4),
                new Ponto(6, 7)
        );
        assertEquals(3.61, new Route(pontos).Comprimento(), 0.01);
    }

    @Test
    void intersectSegmentoReta() {
        List<Ponto> pontos = List.of(
                new Ponto(0, 1),
                new Ponto(1, 1),
                new Ponto(4, 4),
                new Ponto(4, 3)
        );
        SegmentoReta seg = new SegmentoReta(new Ponto(2, 1), new Ponto(2, 4));
        List<Ponto> esperado = List.of(new Ponto(2, 2));
        assertEquals(esperado, new Route(pontos).Intersect(seg));

        pontos = List.of(
                new Ponto(4, 4),
                new Ponto(6, 6),
                new Ponto(7, 7)
        );
        seg = new SegmentoReta(new Ponto(2, 2), new Ponto(4, 2));
        assertNull(new Route(pontos).Intersect(seg));
    }

    @Test
    void intersectPoligono() {
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
        assertEquals(objetivo, rota.Intersect(quadrado));

        rota = new Route(List.of(
                new Ponto(0, 2),
                new Ponto(3, 2),
                new Ponto(5, 0)
        ));
        Triangulo triangulo = new Triangulo(new Ponto[]{
                new Ponto(1, 1),
                new Ponto(3, 3),
                new Ponto(5, 1),
        });
        objetivo = List.of(
                new Ponto(2, 2),
                new Ponto(4, 1)
        );
        assertEquals(objetivo, rota.Intersect(triangulo));

        rota = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(1, 1)
        ));
        triangulo = new Triangulo(new Ponto[]{
                new Ponto(5, 5),
                new Ponto(10, 5),
                new Ponto(7, 10)
        });
        assertNull(rota.Intersect(triangulo));
    }

    @Test
    void intersectCirculo() {
        Route rota = new Route(List.of(
                new Ponto(0, 2),
                new Ponto(10, 2)
        ));
        Circulo circulo = new Circulo(new Ponto(5, 2), 2);
        List<Ponto> objetivo = List.of(
                new Ponto(3, 2),
                new Ponto(7, 2)
        );
        assertEquals(objetivo, rota.Intersect(circulo));
    }


    @Test
    void posicao() {
        Route rota = new Route(List.of(
                new Ponto(5, 1),
                new Ponto(5, 5),
                new Ponto(7, 5)
        ));
        Ponto esperado = new Ponto(5.5, 5);
        assertEquals(esperado, rota.posicao(2, 2.25));

        rota = new Route(List.of(
                new Ponto(100, 100),
                new Ponto(75, 100),
                new Ponto(50, 50),
                new Ponto(25, 50)
        ));
        esperado = new Ponto(62.48, 74.96);
        Ponto resultado = rota.posicao(20, 2.65);

        assertEquals(esperado.getX(), resultado.getX(), 0.01);
        assertEquals(esperado.getY(), resultado.getY(), 0.01);
    }

    @Test
    void velocidadePorSegmento() {
        Route rota = new Route(List.of(
                new Ponto(5, 1),
                new Ponto(5, 5),
                new Ponto(7, 5)
        ));
        List<Vetor> esperado = List.of(
                new Vetor(-1, 1),
                new Vetor(1, -1)
        );
        List<Vetor> resultado = rota.velocidadePorSegmento(new Vetor(1, 1), 2);

        assertListasVetorIguais(esperado, resultado);

        rota = new Route(List.of(
                new Ponto(100, 100),
                new Ponto(75, 100),
                new Ponto(50, 50),
                new Ponto(25, 50)
        ));
        esperado = List.of(
                new Vetor(-21, -2),
                new Vetor(-9.94, -19.89), // Aproximado
                new Vetor(-21, -2)
        );
        resultado = rota.velocidadePorSegmento(new Vetor(1, 2), 20);

        assertListasVetorIguais(esperado, resultado);
    }

    private void assertListasVetorIguais(List<Vetor> esperado, List<Vetor> resultado) {
        assertEquals(esperado.size(), resultado.size(), "As listas têm tamanhos diferentes");

        for (int i = 0; i < esperado.size(); i++) {
            assertEquals(esperado.get(i).getX(), resultado.get(i).getX(), 0.01, "Erro no X do vetor " + i);
            assertEquals(esperado.get(i).getY(), resultado.get(i).getY(), 0.01, "Erro no Y do vetor " + i);
        }
    }

    @Test
    void tempoParaPercorrer() {
        Route rota1 = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(10, 0)
        ));
        assertEquals(5.0, rota1.tempoParaPercorrer(2.0), 0.01);

        Route rota2 = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(0, 4),
                new Ponto(3, 4)
        ));
        assertEquals(3.5, rota2.tempoParaPercorrer(2.0), 0.01);

        Route rota3 = new Route(List.of(
                new Ponto(5, 1),
                new Ponto(5, 5),
                new Ponto(7, 5)
        ));
        assertEquals(3.0, rota3.tempoParaPercorrer(2.0), 0.01);

        Route rota4 = new Route(List.of(
                new Ponto(100, 100),
                new Ponto(75, 100),
                new Ponto(50, 50),
                new Ponto(25, 50)
        ));
        assertEquals(5.295, rota4.tempoParaPercorrer(20.0), 0.01);
    }
}