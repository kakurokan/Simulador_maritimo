import java.util.Iterator;
import java.util.List;

public class Porto {
    private String nome;
    private Ponto origem;
    private List<Navio> naviosEmEspera;
    private TorreDeControlo torre;

    public Porto(String nome, List<Navio> naviosEmEspera, TorreDeControlo torre, Ponto origem) {
        this.nome = nome;
        this.naviosEmEspera = naviosEmEspera;
        this.torre = torre;
        this.origem = new Ponto(origem.getX(), origem.getY());
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
