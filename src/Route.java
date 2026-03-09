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

    public Ponto Intersecao(SegmentoReta sr) {
        Vetor vetor = new Vetor(sr.getA(), sr.getB());

        for (SegmentoReta segmentoRota : retas) {
            Ponto p;
            p = segmentoRota.intersect(vetor);

            //Caso haja alguma interseção, retornamos
            if (p != null)
                return p;
        }

        return null;
    }
}
