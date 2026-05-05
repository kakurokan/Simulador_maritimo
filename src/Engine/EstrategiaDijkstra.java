package Engine;

import java.util.*;


public class EstrategiaDijkstra implements EstrategiaRota {
    private final Grafo grafo;

    EstrategiaDijkstra(Grafo grafo) {
        if (grafo == null){
            throw new IllegalArgumentException("EstrategiaDijkstra:iv");
        }
        this.grafo = grafo;
    }

    private boolean caminhoLivre(SegmentoReta segmento, List<Navio> navios) {
        return true;
    }

    private Route inverteCaminho(Map<Ponto, Ponto> caminhoInverso, Ponto destino) {
        LinkedList<Ponto> caminhoCerto = new LinkedList<>();
        Ponto passo = destino;

        if (caminhoInverso.get(passo) == null) return null;

        while (passo != null) {
            caminhoCerto.addFirst(passo);
            passo = caminhoInverso.get(passo);
        }
        List<Ponto> pontosRota = new ArrayList<>(caminhoCerto);
        return new Route(pontosRota);
    }

    @Override
    public Route caminhos(Ponto origem, Ponto destino, List<Navio> navios) {
        if (!grafo.getGrafo().containsKey(origem) || !grafo.getGrafo().containsKey(destino)) {
            return null;
        }

        TreeMap<Ponto, Double> distancias = new TreeMap<>(Grafo.comparador);

        TreeMap<Ponto, Ponto> caminhoInverso = new TreeMap<>(Grafo.comparador);

        PriorityQueue<No> pq = new PriorityQueue<>();

        for (Ponto p : grafo.getGrafo().keySet()) {
            distancias.put(p, Double.MAX_VALUE);
        }
        distancias.put(origem, 0.0);
        pq.add(new No(origem, 0.0));

        while (!pq.isEmpty()) {
            No n = pq.poll();
            Ponto pontoN = n.ponto;
            double distanciaN = n.distanciaAcumulada;

            Set<Ponto> vizinhos = grafo.getGrafo().get(pontoN);
            if (vizinhos == null) continue;

            for (Ponto p : vizinhos) {
                double distanciaParaP = pontoN.distanciaPara(p);

                if (!caminhoLivre(new SegmentoReta(pontoN, p), navios)) continue;

                double distanciaAcumuladaParaP = distanciaN + distanciaParaP;
                if (distanciaAcumuladaParaP < distancias.get(p)) {
                    distancias.put(p, distanciaAcumuladaParaP);
                    caminhoInverso.put(p, pontoN);
                    pq.offer(new No(p, distanciaAcumuladaParaP));
                }
            }
        }
        return inverteCaminho(caminhoInverso, destino);
    }

    private static class No implements Comparable<No> {
        private final Ponto ponto;
        private final double distanciaAcumulada;

        No(Ponto ponto, double distanciaAcumulada) {
            this.ponto = ponto;
            this.distanciaAcumulada = distanciaAcumulada;
        }


        @Override
        public int compareTo(No n) {
            return Double.compare(this.distanciaAcumulada, n.distanciaAcumulada);
        }
    }

}
