package Engine;

import java.util.List;
import java.util.Random;

public class Simulador {
    private static final double MAX_RAIO_TEMPESTADE = 50;
    private static final double MIN_RAIO_TEMPESTADE = 5;
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

    public void criarTempestade() {
        Random rand = new Random();

        double raioAleatorio = MIN_RAIO_TEMPESTADE + rand.nextDouble() * (MAX_RAIO_TEMPESTADE - MIN_RAIO_TEMPESTADE);

        Route rotaAleatoria = rotas.get(rand.nextInt(rotas.size()));
        List<SegmentoReta> segmentosRotaAleatoria = rotaAleatoria.getSegmentos();
        SegmentoReta segmentoAleatorio = segmentosRotaAleatoria.get(rand.nextInt(segmentosRotaAleatoria.size()));

        Vetor direcaoSegmento = new Vetor(segmentoAleatorio.getA(), segmentoAleatorio.getB());

        double t = rand.nextDouble();

        double x = segmentoAleatorio.getA().getX() + direcaoSegmento.getX() * t;
        double y = segmentoAleatorio.getA().getY() + direcaoSegmento.getY() * t;

        Ponto centroAleatorio = new Ponto(x, y);

        Circulo area = new Circulo(centroAleatorio, raioAleatorio);

        Tempestade tempestade = new Tempestade(area);

        this.obstaculos.add(tempestade);
    }

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }
}
