import java.util.ArrayList;
import java.util.List;

/**
 * @author Léo Souza
 * @version 09/03/26
 * @inv A rota deve ter pelo menos dois pontos e dois pontos consecutivos da rota devem ser diferentes
 */
public class Route {
    private final List<SegmentoReta> retas;

    /**
     * Constrói uma instância de {@code Route} utilizando uma lista de segmentos de reta.
     * A rota é composta por uma sequência de segmentos que devem ser definidos previamente.
     *
     * @param retas A lista de segmentos de reta que formam a rota. Deve conter pelo menos dois {@code SegmentoReta},
     *              e nenhum par de pontos consecutivos na lista pode ser idêntico.
     * @pre Dois pontos consecutivos da lista {@code retas} devem ser diferentes.
     */
    public Route(List<SegmentoReta> retas) {
        this.retas = List.copyOf(retas);
    }

    /**
     * Calcula o comprimento total da rota, somando os comprimentos de todos os segmentos de reta
     * que compõem a rota. O comprimento de cada segmento é calculado individualmente e acumulado
     * para determinar o comprimento total.
     *
     * @return o comprimento total da rota como um valor do tipo {@code double}.
     */
    public double Comprimento() {
        double total = 0;

        for (SegmentoReta sr : retas) {
            total += sr.Comprimento();
        }

        return total;
    }

    /**
     * Calcula os pontos de interseção entre um segmento de reta fornecido e os segmentos de reta
     * que compõem a rota. Se houver interseções, os pontos são retornados em uma lista na ordem
     * em que foram encontrados. Caso contrário, o método retorna {@code null}.
     *
     * @param sr O segmento de reta que será testado para interseções com os segmentos da rota.
     * @return Uma lista de objetos {@code Ponto} representando os pontos de interseção entre
     * o segmento fornecido e os segmentos da rota, ou {@code null} se nenhuma interseção
     * for encontrada.
     */
    public List<Ponto> Intersect(SegmentoReta sr) {
        ArrayList<Ponto> intersecoes = new ArrayList<>();

        for (SegmentoReta segmentoRota : retas) {
            Ponto p = segmentoRota.intersect(sr);
            if (p != null)
                intersecoes.add(p);
        }

        return (intersecoes.isEmpty()) ? null : intersecoes;
    }
}
