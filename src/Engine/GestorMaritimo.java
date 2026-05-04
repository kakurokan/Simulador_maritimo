package Engine;

import java.util.ArrayList;
import java.util.List;

public class GestorMaritimo implements TorreDeControlo {
    private final List<Navio> navios;
    private EstrategiaRota estrategiaRota;
    private Grafo grafo;

    public GestorMaritimo() {
        this.navios = new ArrayList<>();
    }

    public void iniciar(List<Route> rotas, List<Obstaculo> obstaculo) {
        grafo = new Grafo(rotas, obstaculo);
        this.estrategiaRota = new EstrategiaDijkstra(grafo);
    }

    @Override
    public void atualizarPosicoes(Navio navio) {
        boolean colidiu = false;
        boolean parado = false;

        for (Navio outro : navios) {
            if (navio != outro && navio.intersect(outro)) {
                colidiu = true;
                outro.setEmColisao(true);

                int compare = navio.compareTo(outro);
                if (compare < 0) {
                    parado = true;
                    break;
                }
            }
        }

        navio.setEmColisao(colidiu);

        if (parado && navio.getEstado() instanceof NavioNavegando) {
            navio.mudarEstado(new NavioAguardando());
        }
    }

    @Override
    public void atualizarRota(Navio navio) {
        if (!navios.contains(navio))
            return;

        Ponto origem = navio.getPosicao();
        SegmentoReta atual = navio.getSegmentoAtual(origem);
        grafo.adicionarPonto(origem, atual);
        Route rota = estrategiaRota.caminhos(origem, navio.getDestino().getPosicao(), this.navios);
        grafo.removerPonto(origem, atual);
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
