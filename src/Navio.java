public class Navio extends ObstaculoMovel implements Comparable<Navio> {
    private String codigoViagem;
    private Porto destino;
    private Navegante navegante;
    private EstadoNavio estado;
    private TorreDeControlo torre;

    public Navio(Circulo area, double velocidadeLinear, Vetor direcao) {
        super(area, velocidadeLinear, direcao);
    }

    public void atualizar(double delta) {

    }

    public void mudarEstado(EstadoNavio estado) {

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
