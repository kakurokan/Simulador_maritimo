import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PortoTest {

    private TorreDeControloAux torre;
    private Porto portoOrigem,portoDestino;

    static class TorreDeControloAux implements TorreDeControlo {
        public void atualizarRota(Navio navio) {}
        public void atualizarPosicoes(Navio navio, Ponto posicao) {}
        public void libertarNavio(Porto origem, Navio navio) {}
        public void navioTerminouPercurso(Navio navio) {}
    }

    @BeforeEach
    void setUp() {
        torre = new TorreDeControloAux();
        portoOrigem = new Porto("Porto de Lisboa", new Ponto(0, 0), torre);
        portoDestino = new Porto("Porto de Faro", new Ponto(10, 10), torre);
    }
    @Test
    void testNaviosProntos() {
        TorreDeControlo torre = new TorreDeControloAux();
        Iterator<Navio> it = portoOrigem.naviosProntos(10.0);

        assertFalse(it.hasNext(), "O hasNext deve ser false na implementação atual.");

        assertNull(it.next(), "O next deve retornar null de acordo com a implementação atual.");
    }

    @Test
    void adicionarNavio() {
        Navio navio= portoOrigem.adicionarNavio(20,10,portoDestino);
        assertNotNull(navio);
    }

    @Test
    void getPosicao() {
        Ponto posicaoEsperada = new Ponto(0,0);
        assertEquals(posicaoEsperada,portoOrigem.getPosicao());
    }

    @Test
    void getNome() {
        String nomeEsperado = "Porto de Lisboa";
        assertEquals(nomeEsperado,portoOrigem.getNome());
    }
}