import java.util.List;

public class Navio implements Comparable<Navio>, Movel {
    private String codigoViagem;
    private Porto destino;
    private Navegante navegante;
    private EstadoNavio estado;
    private TorreDeControlo torre;
    private double velocidadeLinear;
    private Circulo area;

    public Navio(Circulo area, double velocidadeLinear, String codigoViagem) {
        this.area = new Circulo(area.getCentro(), area.getRaio());
        this.codigoViagem = codigoViagem;
        this.velocidadeLinear = velocidadeLinear;
        this.navegante = new Navegante();
        this.estado = new NavioNaOrigem();
    }

    public void setDestino(Porto destino) {
        this.destino = destino;
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

    public void setTorre(TorreDeControlo torre) {
        this.torre = torre;
    }

    public void receberRota(Route rota) {
        this.navegante.mudarRota(rota);
    }

    @Override
    public int compareTo(Navio o) {
        return this.codigoViagem.compareTo(o.getCodigoViagem());
    }

    public List<SegmentoReta> getSegmentosRota(){
        return navegante.getSegmentos();
    }

    public EstadoNavio getEstado(){
        return this.estado;
    }
}
