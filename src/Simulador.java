import java.util.List;

public class Simulador {
    private double delta;
    private Vetor corrente;
    private List<Route> rotas;
    private List<Tempestade> tempestades;
    private List<Porto> portos;
    private List<ObstaculoEstatico> obstaculoEstaticos;
    private List<Navio> navios;

    public Simulador(double delta, Vetor corrente, List<Route> rotas, List<Tempestade> tempestades, List<Porto> portos, List<ObstaculoEstatico> obstaculoEstaticos, List<Navio> navios) {
        this.delta = delta;
        this.corrente = corrente;
        this.rotas = rotas;
        this.tempestades = tempestades;
        this.portos = portos;
        this.obstaculoEstaticos = obstaculoEstaticos;
        this.navios = navios;
    }

    public void atualizar() {
    }

    public List<Route> getRotas() {
        return rotas;
    }

    public List<Tempestade> getTempestades() {
        return tempestades;
    }

    public List<ObstaculoEstatico> getObstaculoEstaticos() {
        return obstaculoEstaticos;
    }

    public List<Navio> getNavios() {
        return navios;
    }
}
