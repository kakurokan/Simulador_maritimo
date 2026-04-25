package Engine;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Porto {
    private final String nome;
    private final TorreDeControlo torre;
    private final Ponto posicao;
    private PriorityQueue<Navio> naviosEmEspera;

    public Porto(String nome, Ponto posicao, TorreDeControlo torre) {
        this.nome = nome;
        this.posicao = posicao;
        this.naviosEmEspera = new PriorityQueue<>(Comparator.comparingInt(Navio::getHorarioPartida));
        this.torre = torre;
    }

    public Navio adicionarNavio(double velocidadeLinear, int horario, Porto destino) {
        Circulo area = new Circulo(this.getPosicao(), 5); //raio temporario
        Navio navio = new Navio(area, velocidadeLinear, horario, this, destino, torre);
        this.naviosEmEspera.add(navio);
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
        private final double tempoAtual;

        public IteradorNaviosProntos(double tempo) {
            this.tempoAtual = tempo;
        }

        @Override
        public boolean hasNext() {
            return !naviosEmEspera.isEmpty() && naviosEmEspera.peek().getHorarioPartida() <= tempoAtual;
        }

        @Override
        public Navio next() {
            return naviosEmEspera.poll();
        }


    }
}
