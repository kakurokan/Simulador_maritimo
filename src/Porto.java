import java.util.HashMap;
import java.util.Iterator;

public class Porto {
    private String nome;
    private HashMap<Integer, Navio> naviosEmEspera;
    private TorreDeControlo torre;
    private Ponto posicao;

    public Porto(String nome, Ponto posicao, TorreDeControlo torre) {
        this.nome = nome;
        this.posicao = posicao;
        this.naviosEmEspera = new HashMap<>();
        this.torre = torre;
    }

    public Navio adicionarNavio(double velocidadeLinear, int horario, Porto destino) {
        Circulo area = new Circulo(this.getPosicao(), 5); //raio temporario
        Navio navio = new Navio(area, velocidadeLinear, horario, this, destino, torre);
        this.naviosEmEspera.put(horario, navio);
        return navio;
    }

    public Ponto getPosicao() {
        return this.posicao;
    }

    public String getNome() {
        return nome;
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
