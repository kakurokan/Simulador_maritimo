import java.util.Iterator;
import java.util.List;

public class Porto {
    private String nome;
    private List<Navio> naviosEmEspera;
    private TorreDeControlo torre;
    private Ponto posicao;

    public Porto(String nome, Ponto posicao, List<Navio> naviosEmEspera, TorreDeControlo torre) {
        this.nome = nome;
        this.posicao = posicao;
        this.naviosEmEspera = naviosEmEspera;
        this.torre = torre;
    }

    public Iterator<Navio> naviosProntos(double tempo) {
        return new IteradorNaviosProntos(tempo);
    }

    private class IteradorNaviosProntos implements Iterator<Navio> {
        private double tempoAtual;
        private int indexAtual;

        public IteradorNaviosProntos(double tempo) {
            this.tempoAtual = tempo;
            this.indexAtual = 0;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Navio next() {
            return null;
        }
    }
}
