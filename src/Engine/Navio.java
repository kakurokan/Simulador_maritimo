package Engine;

import java.util.List;

public class Navio implements Comparable<Navio>, Movel {
    private final String codigoViagem;
    private final Porto destino;
    private final Navegante navegante;
    private final TorreDeControlo torre;
    private final double velocidadeLinear;
    private final Circulo area;
    private final int horarioPartida;
    private EstadoNavio estado;
    private double tempoNavegando;

    public Navio(Circulo area, double velocidadeLinear, int horario, Porto origem, Porto destino, TorreDeControlo torre) {
        this.area = new Circulo(area.getCentro(), area.getRaio());
        this.destino = destino;
        this.codigoViagem = origem.getNome()+horario;
        this.horarioPartida = horario;
        this.velocidadeLinear = velocidadeLinear;
        this.navegante = new Navegante();
        this.estado = new NavioNaOrigem();
        this.torre = torre;
        this.tempoNavegando = 0;
    }

    private boolean intersect(Circulo circulo) {
        return this.area.intersect(circulo);
    }

    @Override
    public boolean intersect(Movel objeto) {
        return this.intersect(objeto.getArea());
    }

    @Override
    public void mover(double delta) {
        tempoNavegando += delta;

        Ponto novaPosicao = navegante.posicao(this.velocidadeLinear, this.tempoNavegando);
        if (novaPosicao != null) {
            this.area.setCentro(novaPosicao);

            torre.atualizarPosicoes(this);

            double tempoTotalViagem = this.navegante.tempoParaPercorrer(velocidadeLinear);

            if (this.tempoNavegando >= tempoTotalViagem) {
                this.torre.navioTerminouPercurso(this);
            }
        }
    }

    @Override
    public Ponto getPosicao() {
        return area.getCentro();
    }

    public String getCodigoViagem() {
        return this.codigoViagem;
    }

    public void atualizar(double delta) {
        estado.atualizar(this, delta);
    }

    @Override
    public Circulo getArea() {
        return this.area;
    }

    public void mudarEstado(EstadoNavio estado) {
        this.estado = estado;
    }

    public void receberRota(Route rota) {
        this.navegante.mudarRota(rota);
    }

    @Override
    public int compareTo(Navio o) {
        return this.codigoViagem.compareTo(o.getCodigoViagem());
    }

    public List<SegmentoReta> getSegmentosRota() {
        return navegante.getSegmentos();
    }

    public EstadoNavio getEstado() {
        return this.estado;
    }

    public Porto getDestino() {
        return this.destino;
    }

    public int getHorarioPartida() {
        return this.horarioPartida;
    }
}
