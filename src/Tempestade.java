import java.util.List;

public class Tempestade implements Obstaculo {
    private Circulo area;

    public Tempestade(List<Route> routes) {
    }

    @Override
    public List<Ponto> intersect(Route rota) {
        return area.intersect(rota);
    }
}
