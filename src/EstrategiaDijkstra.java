import java.util.List;
import java.util.Map;

public class EstrategiaDijkstra implements EstrategiaRota {
    EstrategiaDijkstra(Grafo grafo) {
    }

    private boolean caminhoLivre(SegmentoReta segmento, List<Tempestade> tempestades, List<Navio> navios) {
        return false;
    }

    private Route inverteCaminho(Map<Ponto, Ponto> caminhoInverso, Ponto destino) {
        return null;
    }

    @Override
    public Route caminhos(Ponto origem, Ponto destino, List<Tempestade> tempestades, List<Navio> navios) {
        return null;
    }

    private class No {
        private Ponto ponto;
        private double distanciaAcumulada;

        No(Ponto ponto, double distanciaAcumulada) {
        }

        public Ponto getPonto() {
            return null;
        }

        public double getDistanciaAcumulada() {
            return distanciaAcumulada;
        }
    }
}
