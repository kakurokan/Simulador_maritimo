package Engine;

import java.util.*;

public class Porto {
    private static final int RAIO_AREA_NAVIO = 5;
    private static final Comparator<Navio> COMPARADOR_POR_HORARIO_PARTIDA =
            Comparator.comparingInt(Navio::getHorarioPartida);
    private final String nome;
    private final TorreDeControlo torre;
    private final Ponto posicao;
    private final PriorityQueue<Navio> naviosEmEspera;

    public Porto(String nome, Ponto posicao, TorreDeControlo torre) {
        this.nome = nome;
        this.posicao = posicao;
        this.torre = torre;
        this.naviosEmEspera = new PriorityQueue<>(COMPARADOR_POR_HORARIO_PARTIDA);
    }

    public Navio adicionarNavio(double velocidadeLinear, int horarioPartida, Porto destino) {
        Navio navio = criarNavio(velocidadeLinear, horarioPartida, destino);
        naviosEmEspera.add(navio);
        return navio;
    }

    private Navio criarNavio(double velocidadeLinear, int horarioPartida, Porto destino) {
        Circulo area = new Circulo(posicao, RAIO_AREA_NAVIO);
        return new Navio(area, velocidadeLinear, horarioPartida, this, destino, torre);
    }

    public void limparFila() {
        this.naviosEmEspera.clear();
    }

    public Ponto getPosicao() {
        return this.posicao;
    }

    public String getNome() {
        return this.nome;
    }

    public List<SnapshotSimulacao.NavioEmEspera> getNaviosEmEspera() {
        List<SnapshotSimulacao.NavioEmEspera> fila = new ArrayList<>();

        for (Navio navio : naviosEmEspera) {
            fila.add(new SnapshotSimulacao.NavioEmEspera(
                    navio.getHorarioPartida(),
                    navio.getDestino().getNome(),
                    navio.getVelocidadeLinear()
            ));
        }

        return fila;
    }

    public Iterator<Navio> naviosProntos(double tempoAtual) {
        return new IteradorNaviosProntos(tempoAtual);
    }

    private class IteradorNaviosProntos implements Iterator<Navio> {
        private final double tempoAtual;

        private IteradorNaviosProntos(double tempoAtual) {
            this.tempoAtual = tempoAtual;
        }

        @Override
        public boolean hasNext() {
            return !naviosEmEspera.isEmpty()
                    && naviosEmEspera.peek().getHorarioPartida() <= tempoAtual;
        }

        @Override
        public Navio next() {
            return hasNext() ? naviosEmEspera.poll() : null;
        }
    }
}
