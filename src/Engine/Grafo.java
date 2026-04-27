package Engine;

import java.util.*;

public class Grafo {
    private final Map<Ponto, Set<Ponto>> grafo;
    static Comparator<Ponto> comparador;

    public Grafo(List<Route> rotas, List<Obstaculo> obstaculo) {
        if(rotas.isEmpty()) {
            throw new IllegalArgumentException("Grafo:iv");
        }

        comparador = Comparator.comparingDouble(Ponto::getX).thenComparingDouble(Ponto::getY);
        grafo =  new TreeMap<>(comparador);
        List<SegmentoReta> segmentosRotas = getSegmentosRotas(rotas);
        for(SegmentoReta segmento : segmentosRotas) {
            if (intersetaObstaculo(segmento,obstaculo)) {
                continue;
            }
            Ponto p1 = segmento.getA();
            Ponto p2 = segmento.getB();
            grafo.computeIfAbsent(p1, k->new TreeSet<>(comparador)).add(p2);
            grafo.computeIfAbsent(p2, k->new TreeSet<>(comparador)).add(p1);
        }
        if(grafo.isEmpty()) {
            throw new IllegalArgumentException("Não existe nenhum segmento livre");
        }
    }

    public Map<Ponto, Set<Ponto>> getGrafo() {
    Map<Ponto, Set<Ponto>> view = new TreeMap<>(comparador);
    for(Map.Entry<Ponto, Set<Ponto>> entry : grafo.entrySet()) {
        view.put(entry.getKey(), Collections.unmodifiableSet(entry.getValue()));
    }
    return Collections.unmodifiableMap(view);}


    private boolean intersetaObstaculo(SegmentoReta segmento, List<Obstaculo> obstaculo) {
        Route rota = new Route(
                List.of(segmento.getA(), segmento.getB()));
        for (Obstaculo o : obstaculo) {
            List<Ponto> pontos = o.intersect(rota);
            if (pontos != null) {
                return true;
            }
        }
        return false;
    }

    private List<SegmentoReta> getSegmentosRotas(List<Route> rotas){
        List<SegmentoReta> segmentos = new ArrayList<>();
        for (Route rota : rotas) {
            segmentos.addAll(rota.getSegmentos());
        }
        return segmentos;
    }
}
