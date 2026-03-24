import java.util.ArrayList;
import java.util.List;

/**
 * A classe AutoPilot representa um sistema de gestão de rotas para calcular posições, tempos e velocidades
 * num percurso determinado. O percurso pode ser definido entre dois pontos ou por uma lista de pontos,
 * sendo decomposto em segmentos representados por vetores.
 *
 * @author Léo Souza
 * @version 24/03/26
 */
public class AutoPilot {
    private final List<Ponto> pontos;
    private final List<Vetor> vetores;

    /**
     * Constrói um objeto da classe AutoPilot representando a rota entre dois pontos.
     *
     * @param a O ponto inicial da rota.
     * @param b O ponto final da rota.
     * @pre a != null, b != null e a != b.
     * @pos O objeto é inicializado com um vetor de rota válido (r != null e r.modulo() > 0)
     */
    public AutoPilot(Ponto a, Ponto b) {
        pontos = new ArrayList<>();
        vetores = new ArrayList<>();
        pontos.add(new Ponto(a.getX(), a.getY()));
        pontos.add(new Ponto(b.getX(), b.getY()));
        vetores.add(new Vetor(a, b));
    }

    /**
     * Constrói um objeto da classe AutoPilot utilizando uma rota composta por múltiplos pontos.
     * A rota é decomposta em vetores representando os segmentos entre os pontos consecutivos.
     *
     * @param rota A rota composta por uma lista de pontos, da qual são gerados os vetores entre
     *             os pontos consecutivos.
     * @pre rota != null e rota.pontos != null
     * @pos O objeto é inicializado com uma nova lista de vetores para cada segmento de reta da rota
     */
    public AutoPilot(Route rota) {
        pontos = rota.getPontos();
        vetores = new ArrayList<>();
        int size = pontos.size() - 1;
        for (int i = 0; i < size; i++) {
            vetores.add(new Vetor(pontos.get(i), pontos.get(i + 1)));
        }
    }

    public Ponto posicao(double linearSpeed, double time) {
        double percorrer = linearSpeed * time;

        for (int i = 0; i < vetores.size(); i++) {
            Vetor segmento = vetores.get(i);

            double distancia = segmento.modulo();

            //Caso ele vá parar no meio do segmento
            if (percorrer <= distancia) {
                double percorreNoSegmento = percorrer / distancia;
                Vetor deslocamento = segmento.mult(percorreNoSegmento);
                Ponto inicio = pontos.get(i);

                return new Ponto(inicio.getX() + deslocamento.getX(),
                        inicio.getY() + deslocamento.getY());
            }

            percorrer -= distancia;
        }

        return pontos.getLast();
    }

    /**
     * Calcula o vetor de velocidade para um segmento específico da rota,
     * levando em conta a influência da velocidade do vento e o tempo especificado.
     *
     * @param windSpeed O vetor que representa a velocidade do vento a ser subtraída
     *                  da velocidade calculada. Não deve ser nulo.
     * @param time      O tempo para percorrer o segmento. Deve ser maior que zero.
     * @return O vetor de velocidade ajustado para o segmento da rota.
     * @pre windSpeed != null e time > 0.0
     */
    public Vetor speed(Vetor windSpeed, double time) {
        Vetor r = new Vetor(pontos.getFirst(), pontos.getLast());
        return speed(windSpeed, time, r);
    }

    /**
     * Calcula o vetor de velocidade ajustado para um segmento específico da rota,
     * considerando a influência da velocidade do vento e o tempo fornecido.
     *
     * @param windSpeed O vetor que representa a velocidade do vento a ser subtraída da velocidade calculada. Não deve ser nulo.
     * @param r         O vetor que representa o segmento da rota. Não deve ser nulo.
     * @param time      O tempo para percorrer o segmento. Deve ser maior que zero.
     * @return O vetor de velocidade para o segmento da rota, ajustado pela velocidade do vento.
     * @pre windSpeed != null, r != null e time > 0.0.
     */
    private Vetor speed(Vetor windSpeed, double time, Vetor r) {
        return r.mult(1 / time).sub(windSpeed);
    }

    /**
     * Calcula as velocidades ajustadas para cada vetor da rota, baseadas na velocidade linear fornecida,
     * corrigidas pela influência de um vetor de velocidade do vento.
     *
     * @param windSpeed   O vetor que representa a velocidade do vento, que irá influenciar as velocidades ajustadas.
     *                    Não deve ser nulo.
     * @param linearSpeed A velocidade linear a ser utilizada no cálculo do tempo para cada vetor.
     *                    Deve ser maior que zero.
     * @return Uma lista de vetores representando as velocidades ajustadas para cada segmento da rota.
     * @pre windSpeed != null, linearSpeed > 0.0
     */
    public List<Vetor> speedPerVector(Vetor windSpeed, double linearSpeed) {
        List<Vetor> velocidades = new ArrayList<>();
        for (Vetor r : vetores) {
            double time = time(linearSpeed, r);
            velocidades.add(speed(windSpeed, time, r));
        }
        return velocidades;
    }

    /**
     * Calcula o tempo necessário para percorrer a rota do vetor com uma velocidade linear específica,
     * ajustando o cálculo pela influência de um vetor de velocidade do vento fornecido.
     *
     * @param linearSpeed A velocidade linear constante utilizada na travessia da rota.
     * @return O tempo necessário para percorrer a rota, ajustado pela velocidade do vento.
     * @pre windSpeed != null e linearSpeed > 0.0
     */
    public double time(double linearSpeed) {
        double resultado = 0;
        for (Vetor r : vetores) {
            resultado += time(linearSpeed, r);
        }
        return resultado;
    }


    /**
     * Calcula o tempo necessário para percorrer um segmento de rota representado por um vetor,
     * dada uma velocidade linear específica.
     *
     * @param linearSpeed A velocidade linear constante utilizada para percorrer o segmento.
     *                    Deve ser maior que zero.
     * @param r           O vetor que representa o segmento de rota a ser percorrido.
     * @return O tempo necessário para percorrer o segmento de rota.
     * @pre linearSpeed > 0.0 e r != null
     */
    private double time(double linearSpeed, Vetor r) {
        return r.modulo() / linearSpeed;
    }
}
