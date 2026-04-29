package Engine;

import java.util.*;

public class Simulador {
    private static final double MAX_RAIO_TEMPESTADE = 50;
    private static final double MIN_RAIO_TEMPESTADE = 5;
    private Vetor corrente;
    private List<Route> rotas;
    private List<Porto> portos;
    private List<Obstaculo> obstaculos;
    private GestorMaritimo gestorMaritimo;
    private double tempoAcumulado;

    public Simulador(Vetor corrente, List<Route> rotas, List<Porto> portos, List<Obstaculo> obstaculo) {
        this.corrente = corrente;
        this.rotas = rotas;
        this.portos = portos;
        this.obstaculos = obstaculo;
        this.gestorMaritimo = new GestorMaritimo(rotas, obstaculo);
        this.tempoAcumulado = 0;
    }

    public void atualizar(double delta) {
        tempoAcumulado += delta;

        for (Porto porto : portos) {
            Iterator<Navio> naviosProntos = porto.naviosProntos(tempoAcumulado);
            while (naviosProntos.hasNext()) {
                Navio navio = naviosProntos.next();

                gestorMaritimo.libertarNavio(porto, navio);
            }
        }

        List<Navio> navios = gestorMaritimo.getNavios();
        for (Navio navio : navios) {
            navio.atualizar(delta);
        }
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

    public SnapshotSimulacao gerarSnapshot() {
        Map<String, List<SnapshotSimulacao.NavioEmEspera>> filaPorPorto = new HashMap<>();
        for (Porto porto : portos) {
            filaPorPorto.put(porto.getNome(), porto.getNaviosEmEspera());
        }

        List<Navio> navios = gestorMaritimo.getNavios();
        List<SnapshotSimulacao.DadosNavio> dadosNavios = new ArrayList<>();

        for (Navio navio : navios) {
            dadosNavios.add(new SnapshotSimulacao.DadosNavio(
                    navio.getPosicao(),
                    navio.getDirecao()
            ));
        }

        return new SnapshotSimulacao(filaPorPorto, dadosNavios);
    }
}
