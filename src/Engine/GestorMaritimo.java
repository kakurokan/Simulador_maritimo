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

        this.navios.clear();
    }

    @Override
    public void atualizarPosicoes(Navio navio) {
        boolean tocando = false;
        boolean caminhoBloqueado = false;

        for (Navio outro : navios) {
            if (navio != outro && navio.intersect(outro)) {
                tocando = true;
                outro.setEmColisao(true); // O outro também acende a bolha visual

                // Verifica se o navio atual tem de ceder passagem (menor prioridade)
                if (navio.compareTo(outro) > 0) {

                    // A MAGIA DA ULTRAPASSAGEM:
                    // Usamos um vetor corrente simulado apenas para descobrir para onde apontam
                    Vetor correnteSimulada = new Vetor(0.001, 0.001);
                    Vetor dirNavio = navio.getDirecao(correnteSimulada);
                    Vetor dirOutro = outro.getDirecao(correnteSimulada);

                    // O Produto Interno (Dot Product) avalia o ângulo entre os dois barcos:
                    // Se <= 0, estão de frente ou de lado (Risco de choque -> Para os motores!)
                    // Se > 0, estão a ir exatamente para o mesmo lado (Pode ultrapassar!)
                    if (dirNavio.produtoInterno(dirOutro) <= 0) {
                        caminhoBloqueado = true;
                    }
                }
            }
        }

        navio.setEmColisao(tocando);

        if (caminhoBloqueado) {
            if (navio.getEstado() instanceof NavioNavegando) {
                navio.mudarEstado(new NavioAguardando());
            }
        } else {
            if (navio.getEstado() instanceof NavioAguardando) {
                navio.mudarEstado(new NavioNavegando());
            }
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
