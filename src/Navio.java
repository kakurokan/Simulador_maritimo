public class Navio implements Comparable<Navio>, Movel {
    private String codigoViagem;
    private Porto destino;
    private Navegante navegante;
    private EstadoNavio estado;
    private TorreDeControlo torre;
    private double velocidadeLinear;
    private Circulo area;

    public Navio(Circulo area, double velocidadeLinear, Vetor direcao, String codigoViagem, Porto destino) {
    }

    @Override
    public boolean intersect(Circulo circulo) {
        return false;
    }

    @Override
    public void mover() {

    }

    @Override
    public Ponto getPosicao() {
        return area.getCentro();
    }

    public void atualizar(double delta) {

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
