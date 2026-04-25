package Engine;

import java.util.ArrayList;
import java.util.List;

public class GestorMaritimo implements TorreDeControlo {
    private final List<Navio> navios;
    private final EstrategiaRota estrategiaRota;

    public GestorMaritimo(List<Route> rotas, List<Obstaculo> obstaculo) {
        Grafo grafo = new Grafo(rotas, obstaculo);
        this.estrategiaRota = new EstrategiaDijkstra(grafo);
        this.navios = new ArrayList<>();
    }

    @Override
    public void atualizarPosicoes(Navio navio) {
        for (Navio outro : navios) {
            if (navio.intersect(outro)) {
                int compare = navio.compareTo(outro);
            }
        }
    }

    @Override
    public void atualizarRota(Navio navio) {
        if (!navios.contains(navio))
            return;

        Ponto origem = navio.getPosicao();
        Route rota = estrategiaRota.caminhos(origem, navio.getDestino().getPosicao(), this.navios);

        if (rota != null) {
            navio.receberRota(rota);
            navio.mudarEstado(new NavioNavegando());
        } else {
            navio.mudarEstado(new NavioAguardando());
        }

    }

    @Override
    public void libertarNavio(Porto origem, Navio navio) {
        Route rota = estrategiaRota.caminhos(origem.getPosicao(), navio.getDestino().getPosicao(), this.navios);
        if (rota != null) {
            navio.receberRota(rota);
            navio.mudarEstado(new NavioNavegando());
            this.navios.add(navio);
        }
    }

    @Override
    public void navioTerminouPercurso(Navio navio) {
        navio.mudarEstado(new NavioNoDestino());
        this.navios.remove(navio);
    }

    public List<Navio> getNavios() {
        return this.navios;
    }
}
