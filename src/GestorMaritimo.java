import java.util.List;

public class GestorMaritimo implements TorreDeControlo {
    private Grafo grafo;
    private List<Navio> navios;
    private List<ObstaculoEstatico> obstaculoEstaticos;
    private List<Tempestade> tempestades;
    private EstrategiaRota estrategiaRota;

    public GestorMaritimo(List<Route> rotas, List<ObstaculoEstatico> obstaculoEstatico, List<Navio> navios, List<Tempestade> tempestades) {
        this.grafo = new Grafo(rotas, obstaculoEstaticos);
        this.estrategiaRota = new EstrategiaDijkstra(this.grafo);
        this.navios = navios;
        this.tempestades = tempestades;
        this.obstaculoEstaticos = obstaculoEstaticos;
    }

    @Override
    public void atualizarRota(Navio navio) {

    }

    @Override
    public void atualizarPosicoes(Navio navio, Ponto posicao) {

    }

    @Override
    public void libertarNavio(Porto origem, Navio navio) {

    }
}
