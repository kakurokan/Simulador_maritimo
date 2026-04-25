package Engine;

import java.util.List;

public class Simulador {
    private Vetor corrente;
    private List<Route> rotas;
    private List<Porto> portos;
    private List<Obstaculo> obstaculo;
    private List<Navio> navios;

    public Simulador(Vetor corrente, List<Route> rotas, List<Porto> portos, List<Navio> navios, List<Obstaculo> obstaculo) {
        this.corrente = corrente;
        this.rotas = rotas;
        this.portos = portos;
        this.obstaculo = obstaculo;
        this.navios = navios;
    }

    public void atualizar(double delta) {
    }

    public Tempestade criarTempestade() {
        return null;
    }
}
