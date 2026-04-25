package Engine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NaveganteTest {


    @Test
    void posicao() {
        Route rota = new Route(List.of(
                new Ponto(5, 1),
                new Ponto(5, 5),
                new Ponto(7, 5)
        ));
        Ponto esperado = new Ponto(5.5, 5);
        Navegante navegante = new Navegante(rota);
        assertEquals(esperado, navegante.posicao(2, 2.25));

        rota = new Route(List.of(
                new Ponto(100, 100),
                new Ponto(75, 100),
                new Ponto(50, 50),
                new Ponto(25, 50)
        ));
        esperado = new Ponto(62.48, 74.96);
        navegante = new Navegante(rota);
        Ponto resultado = navegante.posicao(20, 2.65);

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
        Navegante navegante = new Navegante(rota);
        List<Vetor> resultado = navegante.velocidadePorSegmento(new Vetor(1, 1), 2);

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
        navegante = new Navegante(rota);
        resultado = navegante.velocidadePorSegmento(new Vetor(1, 2), 20);

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
        Route rota = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(10, 0)
        ));
        Navegante navegante = new Navegante(rota);
        assertEquals(5.0, navegante.tempoParaPercorrer(2.0), 0.01);

        rota = new Route(List.of(
                new Ponto(0, 0),
                new Ponto(0, 4),
                new Ponto(3, 4)
        ));
        navegante = new Navegante(rota);
        assertEquals(3.5, navegante.tempoParaPercorrer(2.0), 0.01);

        rota = new Route(List.of(
                new Ponto(5, 1),
                new Ponto(5, 5),
                new Ponto(7, 5)
        ));
        navegante = new Navegante(rota);
        assertEquals(3.0, navegante.tempoParaPercorrer(2.0), 0.01);

        rota = new Route(List.of(
                new Ponto(100, 100),
                new Ponto(75, 100),
                new Ponto(50, 50),
                new Ponto(25, 50)
        ));
        navegante = new Navegante(rota);
        assertEquals(5.295, navegante.tempoParaPercorrer(20.0), 0.01);
    }

    @Test
    void mudarRota() {
        Route rotaInicial = new Route(
          List.of(
                  new Ponto(0,0), new Ponto(1,1), new Ponto(2,3)
          )
        );
        Navegante navegante = new Navegante(rotaInicial);

        Route rotaNova = new Route(List.of(
           new Ponto (0,0), new Ponto(2,4), new Ponto(4,5)
        ));

        navegante.mudarRota(rotaNova);

        assertEquals(rotaNova.getSegmentos(),navegante.getSegmentos());
    }

    @Test
    void getSegmentos() {
        Route rotaInicial = new Route(
                List.of(
                        new Ponto(0,0), new Ponto(1,1), new Ponto(2,3)
                )
        );
        Navegante navegante = new Navegante(rotaInicial);
        assertEquals(rotaInicial.getSegmentos(),navegante.getSegmentos());
    }
}
