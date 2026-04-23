import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class PortoTest {

    static class TorreDeControloAux implements TorreDeControlo {
        public void atualizarRota(Navio navio) {}
        public void atualizarPosicoes(Navio navio, Ponto posicao) {}
        public void libertarNavio(Porto origem, Navio navio) {}
    }

    @Test
    void testNaviosProntos() {
        List<Navio> lista = new ArrayList<>();
        TorreDeControlo torre = new TorreDeControloAux();
        Porto porto = new Porto("Porto Lisboa", lista, torre);

        Iterator<Navio> it = porto.naviosProntos(10.0);

        assertFalse(it.hasNext(), "O hasNext deve ser false na implementação atual.");

        assertNull(it.next(), "O next deve retornar null de acordo com a implementação atual.");
    }
}