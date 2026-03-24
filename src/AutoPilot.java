import java.util.ArrayList;
import java.util.List;

/**
 * @author Léo Souza
 * @version 24/02/26
 * @inv o vetor r não pode ser nulo
 * A classe AutoPilot representa um sistema de navegação automatizado que calcula
 * a velocidade ajustada e o tempo necessário para percorrer uma rota entre dois pontos,
 * levando em consideração fatores como velocidade do vento.
 */
public class AutoPilot {
    private List<Ponto> pontos;
    private List<Vetor> vetores;

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

    public Ponto posicao(double time) {
    
    }

    /**
     * Calcula as velocidades ajustadas de cada vetor da rota com base no tempo fornecido,
     * corrigidas pelo vetor de velocidade do vento.
     *
     * @param windSpeed O vetor de velocidade do vento que será subtraído de cada vetor de velocidade calculado.
     * @param time      O tempo para calcular a velocidade ajustada. Deve ser maior que zero.
     * @return Uma lista de vetores representando as velocidades ajustadas de cada segmento da rota.
     * @pre windSpeed != null e time > 0.0
     */
    public List<Vetor> speed(Vetor windSpeed, List<Double> time) {
        ArrayList<Vetor> velocidades = new ArrayList<>();
        for (int i = 0; i < vetores.size(); i++) {
            velocidades.add(vetores.get(i).mult(1 / time.get(i)).sub(windSpeed));
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
            resultado += r.modulo() / linearSpeed;
        }
        return resultado;
    }
}
