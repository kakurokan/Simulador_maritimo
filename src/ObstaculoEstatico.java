import java.util.List;

public abstract class ObstaculoEstatico implements Obstaculo {
    abstract public List<Ponto> intersect(Route rota);
}
