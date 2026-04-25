import java.util.ArrayList;
import java.util.List;

public class GestorMaritimo implements TorreDeControlo {
    private Grafo grafo;
    private List<Navio> navios;
    private List<Obstaculo> obstaculo;
    private EstrategiaRota estrategiaRota;

    public GestorMaritimo(List<Route> rotas, List<Obstaculo> obstaculo) {
        this.grafo = new Grafo(rotas, obstaculo);
        this.estrategiaRota = new EstrategiaDijkstra(this.grafo);
        this.obstaculo = obstaculo;
        this.navios = new ArrayList<>();
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

    @Override
    public void navioTerminouPercurso(Navio navio) {

    }

    public List<Navio> getNavios() {
        return this.navios;
    }
}
