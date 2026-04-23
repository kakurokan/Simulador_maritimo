import java.util.List;

public class Simulador {
    private double delta;
    private Vetor corrente;
    private List<Route> rotas;
    private List<Tempestade> tempestades;
    private List<Porto> portos;
    private List<Obstaculo> obstaculo;
    private List<Navio> navios;

    public Simulador(double delta, Vetor corrente, List<Route> rotas, List<Tempestade> tempestades, List<Porto> portos, List<Obstaculo> obstaculo, List<Navio> navios) {
        this.delta = delta;
        this.corrente = corrente;
        this.rotas = rotas;
        this.tempestades = tempestades;
        this.portos = portos;
        this.obstaculo = obstaculo;
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

    public List<Obstaculo> getObstaculoEstaticos() {
        return obstaculo;
    }

    public List<Navio> getNavios() {
        return navios;
    }
}
