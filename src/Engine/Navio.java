package Engine;

import java.util.List;

public class Navio implements Comparable<Navio>, Movel {
    private String codigoViagem;
    private Porto destino;
    private Navegante navegante;
    private EstadoNavio estado;
    private TorreDeControlo torre;
    private double velocidadeLinear;
    private Circulo area;
    private double tempoNavegando;

    public Navio(Circulo area, double velocidadeLinear, int horario, Porto origem, Porto destino, TorreDeControlo torre) {
        this.area = new Circulo(area.getCentro(), area.getRaio());
        this.destino = destino;
        this.codigoViagem = horario + origem.getNome();
        this.velocidadeLinear = velocidadeLinear;
        this.navegante = new Navegante();
        this.estado = new NavioNaOrigem();
        this.torre = torre;
        this.tempoNavegando = 0;
       }

    @Override
    public boolean intersect(Circulo circulo) {
        return this.area.intersect(circulo);
    }

    @Override
    public void mover() {

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

    public Porto getDestino(){
        return this.destino;
    }
}
