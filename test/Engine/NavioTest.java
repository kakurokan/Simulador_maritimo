package Engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NavioTest {

    private Porto origem, destino;
    private Navio navio, navioInterseta;
    private TorreDeControlo torre;

    @BeforeEach
    void setUp() {
        Route melhorRotaEsperada = new Route(List.of(
                new Ponto(0,0), new Ponto(1,1), new Ponto(3,2), new Ponto(3,5)
        ));
        List<Route> rotas = List.of(melhorRotaEsperada);
        List<Obstaculo> obstaculos = new ArrayList<>();
        torre = new GestorMaritimo(rotas,obstaculos);
        origem = new Porto("Porto de Lisboa", new Ponto(0, 0), torre);
        destino = new Porto("Porto de Faro", new Ponto(10, 10), torre);
        navio = origem.adicionarNavio(5, 2, destino);
        navioInterseta = new Navio(new Circulo(new Ponto(1, 1), 1), 20, 5, origem, destino, torre);
    }

    @Test
    void intersect() {
        assertTrue(navio.intersect(new Circulo(navioInterseta.getPosicao(), 1)));
    }

    @Test
    void mover() {
        double delta = 0.5;
        torre.atualizarRota(navio);
        navio.mover(delta);
        Ponto posicaoAtual = navio.getPosicao();

        assertEquals(1.971156914, posicaoAtual.getX(), Ponto.eps);
        assertEquals(1.485578457, posicaoAtual.getY(), Ponto.eps);

    }

    @Test
    void getPosicao() {
        Ponto posicaoEsperada = new Ponto(1, 1);
        assertEquals(posicaoEsperada, navioInterseta.getPosicao());
    }

    @Test
    void getCodigoViagem() {
        String codigoEsperado = "Porto de Lisboa2";
        assertEquals(codigoEsperado, navio.getCodigoViagem());
    }

    @Test
    void atualizar() {

    }

    @Test
    void mudarEstado() {
        EstadoNavio estado = new NavioNoDestino();
        navio.mudarEstado(estado);
        assertInstanceOf(NavioNoDestino.class, navio.getEstado());
    }

    @Test
    void receberRota() {
        Route rota1 = new Route(List.of(
                new Ponto(0, 0), new Ponto(1, 1), new Ponto(3, 2), new Ponto(3, 5)
        ));
        navio.receberRota(rota1);
        assertEquals(rota1.getSegmentos(), navio.getSegmentosRota());
    }

    @Test
    void compareTo() {
        assertTrue(navio.compareTo(navioInterseta) < 0);
    }

    @Test
    void getEstado() {
        assertInstanceOf(NavioNaOrigem.class, navio.getEstado());
    }

    @Test
    void gestDestino() {
        assertEquals(destino, navio.getDestino());
    }



}