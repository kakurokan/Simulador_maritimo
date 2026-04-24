import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class EstadoNavioTest {
    private Navio navio;

    static class TorreDeControloSAux implements TorreDeControlo {
        @Override
        public void atualizarRota(Navio navio) {}

        @Override
        public void atualizarPosicoes(Navio navio, Ponto posicao) {}

        @Override
        public void libertarNavio(Porto origem, Navio navio) {}
    }

    @BeforeEach
    void setUp() {
        Ponto centro = new Ponto(0, 0);
        Circulo areaNavio = new Circulo(centro, 10.0);
        Vetor direcao = new Vetor(1, 1);

        TorreDeControlo torreAux = new TorreDeControloSAux();
        Porto portoDestino = new Porto("Porto Faro", new Ponto(100.0, 100.0), new ArrayList<>(), torreAux);

        navio = new Navio(areaNavio, 20.0, "NAV-001");
    }

    @Test
    void testNavioNaOrigem() {
        EstadoNavio naOrigem = new NavioNaOrigem();

        assertDoesNotThrow(() -> navio.mudarEstado(naOrigem));
        assertDoesNotThrow(() -> naOrigem.atualizar(navio, 1.0));
        assertInstanceOf(EstadoNavio.class, naOrigem);
    }

    @Test
    void testNavioAguardando() {
        EstadoNavio aguardando = new NavioAguardando();

        assertDoesNotThrow(() -> navio.mudarEstado(aguardando));
        assertDoesNotThrow(() -> aguardando.atualizar(navio, 5.0));
        assertInstanceOf(EstadoNavio.class, aguardando);
    }

    @Test
    void testNavioNavegando() {
        EstadoNavio navegando = new NavioNavegando();

        assertDoesNotThrow(() -> navio.mudarEstado(navegando));
        assertDoesNotThrow(() -> navegando.atualizar(navio, 10.0));
        assertInstanceOf(EstadoNavio.class, navegando);

        assertInstanceOf(Movel.class, navio, "O navio deve ser Movel para poder navegar.");
    }

    @Test
    void testNavioNoDestino() {
        EstadoNavio noDestino = new NavioNoDestino();

        assertDoesNotThrow(() -> navio.mudarEstado(noDestino));
        assertDoesNotThrow(() -> noDestino.atualizar(navio, 2.0));
        assertInstanceOf(EstadoNavio.class, noDestino);
    }


    @Test
    @Disabled
    void testNavioMovePraNovaPosicao() {
        EstadoNavio navegando = new NavioNavegando();
        navio.mudarEstado(navegando);

        navegando.atualizar(navio, 1.0);

        Ponto posicaoAtual = navio.getPosicao();

        assertNotNull(posicaoAtual, "A posição não deveria ser nula. O navio deve ter um centro.");

        assertEquals(20.0, posicaoAtual.getX(), 0.01, "A coordenada X do navio deveria ser 20.0.");
        assertEquals(20.0, posicaoAtual.getY(), 0.01, "A coordenada Y do navio deveria ser 20.0.");
    }
}