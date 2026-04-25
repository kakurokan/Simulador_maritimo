package Engine;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo {
    private Map<Ponto, Set<Ponto>> grafo;

    public Grafo(List<Route> rotas, List<Obstaculo> obstaculo) {
        if(rotas.isEmpty()) {
            throw new IllegalArgumentException("Grafo:iv");
        }
        /* Implementação*/
        if(grafo.isEmpty()) {
            throw new IllegalArgumentException("Não existe nenhum segmento livre");
        }
    }

    public Map<Ponto, Set<Ponto>> getGrafo() {
        return grafo;
    }
}
