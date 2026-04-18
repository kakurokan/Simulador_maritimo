import java.util.ArrayList;
import java.util.List;

/**
 * Representa um círculo no sistema de coordenadas cartesianas definido
 * por um ponto central e um raio.
 *
 * @author Léo Souza
 * @version 12/03/26
 * @inv o raio têm que ser maior que zero
 */
public class Circulo implements Obstaculo {
    private final Ponto centro;
    private final double raio;

    /**
     * Constrói um objeto Circulo com um ponto central e um raio especificado.
     *
     * @param centro O ponto representando o centro do círculo. Não pode ser nulo.
     * @param raio   O raio do círculo. Deve ser um valor positivo.
     * @throws IllegalArgumentException Se o raio não for um valor positivo válido
     * @pre raio > 0 e centro != null
     * @pos getRaio() = raio
     * @pos getCentro() = uma cópia do objeto centro
     */
    public Circulo(Ponto centro, double raio) {
        if (raio < Ponto.eps) {
            throw new IllegalArgumentException("Circulo:iv");
        }

        this.centro = new Ponto(centro.getX(), centro.getY());
        this.raio = raio;
    }

    /**
     * Retorna o ponto representando o centro do círculo no sistema de coordenadas cartesianas.
     * Este método fornece acesso ao ponto central armazenado internamente.
     *
     * @return o ponto que representa o centro do círculo.
     */
    public Ponto getCentro() {
        return centro;
    }

    /**
     * Retorna o valor do raio do círculo.
     * O raio é sempre um valor positivo que representa a distância do centro
     * do círculo à sua borda.
     *
     * @return o valor do raio do círculo.
     */
    public double getRaio() {
        return raio;
    }

    /**
     * Calcula os pontos de interseção entre o círculo atual e uma rota fornecida.
     * Uma rota é definida por uma coleção de segmentos de reta, e o método verifica
     * todos os segmentos da rota para identificar os pontos de interseção únicos
     * com o círculo atual.
     *
     * @param rota A rota contendo os segmentos de reta a serem verificados quanto à interseção
     *             com o círculo. Não pode ser nula.
     * @return Uma lista de pontos de interseção únicos entre o círculo e os segmentos da rota,
     * ou {@code null} se não houver interseções.
     * @pre rota != null
     */
    @Override
    public List<Ponto> intersect(Route rota) {
        ArrayList<Ponto> intersecoes = new ArrayList<>();
        List<SegmentoReta> segmentosRota = rota.getSegmentos();

        for (SegmentoReta segmentoRota : segmentosRota) {
            List<Ponto> pontosIntersecao = segmentoRota.intersect(this);

            for (Ponto intersecao : pontosIntersecao) {
                if (!intersecoes.contains(intersecao)) {
                    intersecoes.add(intersecao);
                }
            }
        }

        return (intersecoes.isEmpty()) ? null : intersecoes;
    }
}
