package Engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorMaritimoTest {

    private GestorMaritimo gestor;
    private Navio navio;
    private Navio navio2;
    private Porto origem, destino;

    @BeforeEach
    void setUp() {
        Route rota1 = new Route(List.of(
                new Ponto(0, 0), new Ponto(1, 1), new Ponto(3, 2), new Ponto(3, 5)
        ));

        Route rota2 = new Route(List.of(
                new Ponto(9, 1), new Ponto(5, 1), new Ponto(3, 2),
                new Ponto(2, 3), new Ponto(0, 4), new Ponto(3, 5)
        ));

        List<Route> rotas = new ArrayList<>(List.of(rota1, rota2));
        List<Obstaculo> obstaculos = new ArrayList<>();


        gestor = new GestorMaritimo(rotas, obstaculos);

        navio = origem.adicionarNavio(20, 10, destino);
        Navio navio1 = destino.adicionarNavio(20, 15, origem);
        navio2 = origem.adicionarNavio(20, 20, destino);
        origem = new Porto("Albufeira", new Ponto(0, 0), gestor);
        destino = new Porto("Lisboa", new Ponto(3, 5), gestor);

        navio = origem.adicionarNavio(20, 10, destino);
        navio2 = origem.adicionarNavio(20, 12, destino);

        gestor = new GestorMaritimo(rotas, obstaculos);
    }

    @Test
    void atualizarRota() {

        gestor.atualizarRota(navio2);

        Route melhorRotaEsperada = new Route(List.of(
                new Ponto(0, 0), new Ponto(1, 1), new Ponto(3, 2), new Ponto(3, 5)
        ));
        assertEquals(melhorRotaEsperada.getSegmentos(), navio2.getSegmentosRota());
    }

    @Test
    void atualizarPosicoes() {

        Ponto posicaoFinal = new Ponto(3, 5);
        gestor.atualizarPosicoes(navio);

        assertEquals(posicaoFinal, navio.getPosicao());
    }

    @Test
    void libertarNavio() {

        Porto porto = new Porto("Albufeira City", new Ponto(0, 0), gestor);
        gestor.libertarNavio(porto, navio2);
        gestor.libertarNavio(origem, navio);

        assertInstanceOf(NavioNavegando.class, navio.getEstado());

        assertInstanceOf(NavioNavegando.class, navio2.getEstado());
    }

    @Test
    void navioTerminouPercurso() {
        gestor.libertarNavio(origem, navio2);
        gestor.atualizarPosicoes(navio2);
        gestor.navioTerminouPercurso(navio2);
        assertFalse(gestor.getNavios().contains(navio2));
    }
}