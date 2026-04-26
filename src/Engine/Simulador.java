package Engine;

import java.util.List;

public class Simulador {
    private Vetor corrente;
    private List<Route> rotas;
    private List<Porto> portos;
    private List<Obstaculo> obstaculos;
    private List<Movel> movels;
    private GestorMaritimo gestorMaritimo;

    public Simulador(Vetor corrente, List<Route> rotas, List<Porto> portos, List<Movel> movels, List<Obstaculo> obstaculo) {
        this.corrente = corrente;
        this.rotas = rotas;
        this.portos = portos;
        this.obstaculos = obstaculo;
        this.movels = movels;
        this.gestorMaritimo = new GestorMaritimo(rotas, obstaculo);
    }

    public void atualizar(double delta) {
    }

    public Tempestade criarTempestade() {
        return null;
    }

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }
}
