public class Navio implements Comparable<Navio>, Movel {
    private String codigoViagem;
    private Porto destino;
    private Navegante navegante;
    private EstadoNavio estado;
    private TorreDeControlo torre;
    private double velocidadeLinear;
    private Circulo area;

    public Navio(Circulo area, double velocidadeLinear, String codigoViagem, Porto destino) {
        this.area = new Circulo(area.getCentro(), area.getRaio());
        this.codigoViagem = codigoViagem;
        this.destino = destino;
        this.velocidadeLinear = velocidadeLinear;
        this.navegante = new Navegante();
        this.estado = new NavioNaOrigem();
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
        return 0;
    }
}
