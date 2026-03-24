import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoPilotTest {


    @Test
    void speed() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        Vetor windSpeed = new Vetor(0.2, 0.2);
        double linearSpeed = 0.4;
        double time = ap.time(linearSpeed);
        Vetor result = ap.speed(windSpeed, time);
        assertEquals(-0.2, result.getX(), Ponto.eps);
        assertEquals(0.2, result.getY(), Ponto.eps);

        ap = new AutoPilot(new Ponto(3, 4), new Ponto(3, 2));
        result = ap.speed(windSpeed, time);
        assertEquals(-0.2, result.getX(), Ponto.eps);
        assertEquals(-0.6, result.getY(), Ponto.eps);
    }


    @Test
    void time() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        double speed = 0.4;
        double result = ap.time(speed);
        assertEquals(5, result, 0.01);
    }

    @Test
    void posicao() {
        Route rota = new Route(List.of(
                new Ponto(5, 1),
                new Ponto(5, 5),
                new Ponto(7, 5)
        ));
        AutoPilot ap = new AutoPilot(rota);
        Ponto esperado = new Ponto(5.5, 5);
        assertEquals(esperado, ap.posicao(2, 2.25));

        rota = new Route(List.of(
                new Ponto(100, 100),
                new Ponto(75, 100),
                new Ponto(50, 50),
                new Ponto(25, 50)
        ));
        ap = new AutoPilot(rota);
        esperado = new Ponto(62.48, 74.96);
        Ponto resultado = ap.posicao(20, 2.65);

        assertEquals(esperado.getX(), resultado.getX(), 0.01);
        assertEquals(esperado.getY(), resultado.getY(), 0.01);
    }

    @Test
    void speedPerVector() {
        Route rota = new Route(List.of(
                new Ponto(5, 1),
                new Ponto(5, 5),
                new Ponto(7, 5)
        ));
        AutoPilot ap = new AutoPilot(rota);
        List<Vetor> esperado = List.of(
                new Vetor(-1, 1),
                new Vetor(1, -1)
        );
        List<Vetor> resultado = ap.speedPerVector(new Vetor(1, 1), 2);

        assertListasVetorIguais(esperado, resultado);

        rota = new Route(List.of(
                new Ponto(100, 100),
                new Ponto(75, 100),
                new Ponto(50, 50),
                new Ponto(25, 50)
        ));
        ap = new AutoPilot(rota);
        esperado = List.of(
                new Vetor(-21, -2),
                new Vetor(-9.94, -19.89), // Aproximado
                new Vetor(-21, -2)
        );
        resultado = ap.speedPerVector(new Vetor(1, 2), 20);

        assertListasVetorIguais(esperado, resultado);
    }

    private void assertListasVetorIguais(List<Vetor> esperado, List<Vetor> resultado) {
        assertEquals(esperado.size(), resultado.size(), "As listas têm tamanhos diferentes");

        for (int i = 0; i < esperado.size(); i++) {
            assertEquals(esperado.get(i).getX(), resultado.get(i).getX(), 0.01, "Erro no X do vetor " + i);
            assertEquals(esperado.get(i).getY(), resultado.get(i).getY(), 0.01, "Erro no Y do vetor " + i);
        }
    }
}