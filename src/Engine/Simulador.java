package Engine;

import java.util.List;

public class Simulador {
    private Vetor corrente;
    private List<Route> rotas;
    private List<Porto> portos;
    private List<Obstaculo> obstaculo;
    private List<Movel> movels;

    public Simulador(Vetor corrente, List<Route> rotas, List<Porto> portos, List<Movel> movels, List<Obstaculo> obstaculo) {
        this.corrente = corrente;
        this.rotas = rotas;
        this.portos = portos;
        this.obstaculo = obstaculo;
        this.movels = movels;
    }

    public void atualizar(double delta) {
    }

    public Tempestade criarTempestade() {
        return null;
    }

    public List<Obstaculo> getObstaculo() {
        return obstaculo;
    }
}
