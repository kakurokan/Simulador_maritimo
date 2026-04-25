package Engine;

import java.util.List;

public class Tempestade implements Obstaculo {
    private Circulo area;

    public Tempestade(Circulo area) {
        this.area = new Circulo(area.getCentro(), area.getRaio());
    }

    @Override
    public List<Ponto> intersect(Route rota) {
        return area.intersect(rota);
    }
}
