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
        assertTrue(navio.intersect(navioInterseta));
    }

    @Test
    void mover() {
        double delta = 0.5;
        torre.atualizarRota(navio);
        Ponto posicaoInicial =navio.getPosicao();

        navio.mover(delta);
        Ponto posicaoAtual = navio.getPosicao();

        assertNotEquals(posicaoInicial, posicaoAtual);
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
        class EstadoNavioTemp implements EstadoNavio{
            boolean foiChamado = false;
            double deltaRecebido =0;
            Navio navioRecebido = null;

            @Override
            public void atualizar(Navio navio, double delta){
                this.foiChamado = true;
                this.navioRecebido = navio;
                this.deltaRecebido = delta;
            }
        }

        EstadoNavioTemp estado = new EstadoNavioTemp();
        navio.mudarEstado(estado);
        navio.atualizar(5);

        assertTrue(estado.foiChamado);
        assertEquals(navio,estado.navioRecebido);
        assertEquals(5,estado.deltaRecebido);
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
    void getDestino() {
        assertEquals(destino, navio.getDestino());
    }


    @Test
    void getHorarioPartida() {
        assertEquals(2,navio.getHorarioPartida());
    }
}