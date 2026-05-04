package Engine;

import java.util.*;

public class Simulador {
    private static final double MAX_RAIO_TEMPESTADE = 1.0;
    private static final double MIN_RAIO_TEMPESTADE = 0.5;
    private final Vetor corrente;
    private final List<Route> rotas;
    private final List<Porto> portos;
    private final List<Obstaculo> obstaculos;
    private final TorreDeControlo torreDeControlo;
    private double tempoAcumulado;

    public Simulador(Vetor corrente, List<Route> rotas, List<Porto> portos, List<Obstaculo> obstaculo, TorreDeControlo torreDeControlo) {
        this.corrente = corrente;
        this.rotas = rotas;
        this.portos = portos;
        this.obstaculos = obstaculo;
        this.torreDeControlo = torreDeControlo;
        this.tempoAcumulado = 0;
    }

    public void iniciar() {
        torreDeControlo.iniciar(this.rotas, this.obstaculos);
    }

    public void atualizar(double delta) {
        tempoAcumulado += delta;

        for (Porto porto : portos) {
            Iterator<Navio> naviosProntos = porto.naviosProntos(tempoAcumulado);
            while (naviosProntos.hasNext()) {
                Navio navio = naviosProntos.next();

                torreDeControlo.libertarNavio(porto, navio);
            }
        }

        List<Navio> navios = torreDeControlo.getNavios();
        for (Navio navio : new ArrayList<>(navios)) {
            navio.atualizar(delta, this.corrente);
        }
    }

    public Tempestade criarTempestade() {
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
        return tempestade;
    }

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public SnapshotSimulacao gerarSnapshot() {
        Map<String, List<SnapshotSimulacao.NavioEmEspera>> filaPorPorto = new HashMap<>();
        for (Porto porto : portos) {
            filaPorPorto.put(porto.getNome(), porto.getNaviosEmEspera());
        }

        List<Navio> navios = torreDeControlo.getNavios();
        List<SnapshotSimulacao.DadosNavio> dadosNavios = new ArrayList<>();

        for (Navio navio : navios) {
            dadosNavios.add(new SnapshotSimulacao.DadosNavio(
                    navio.getPosicao(),
                    navio.getDirecao(this.corrente),
                    navio.isEmColisao(),
                    navio.getArea().getRaio()
            ));
        }

        return new SnapshotSimulacao(filaPorPorto, dadosNavios, this.tempoAcumulado);
    }

    public List<Tempestade> reiniciarSimulacao() {
        this.tempoAcumulado = 0;

        for (Porto porto : portos) {
            porto.limparFila();
        }

        // Remover apenas as tempestades
        this.obstaculos.removeIf(obs -> obs instanceof Tempestade);

        // Cria tempestades aleatórias
        List<Tempestade> novasTempestades = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            novasTempestades.add(criarTempestade());
        }

        this.torreDeControlo.iniciar(this.rotas, this.obstaculos);

        return novasTempestades;
    }
}
