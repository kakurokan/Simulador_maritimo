package Engine;

import java.util.List;

public class Tempestade implements Obstaculo {
    private final Circulo area;

    public Tempestade(Circulo area) {
        this.area = new Circulo(area.getCentro(), area.getRaio());
    }

    public Circulo getArea() {
        return area;
    }

    @Override
    public List<Ponto> intersect(Route rota) {
        return area.intersect(rota);
    }
}
