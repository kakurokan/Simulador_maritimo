import java.util.ArrayList;
import java.util.List;

/**
 * @author Léo Souza
 * @version 09/03/26
 * @inv A rota deve ter pelo menos dois pontos e dois pontos consecutivos da rota devem ser diferentes
 */
public class Route {
    private final List<Ponto> pontos;

    public Route(List<Ponto> pontos) {
        this.pontos = List.copyOf(pontos);
    }

    /**
     * Calcula o comprimento total da rota representada pelo conjunto de pontos.
     * O comprimento é a soma das distâncias entre pares consecutivos de pontos
     * na sequência de pontos que compõem a rota.
     *
     * @return O comprimento total da rota como um valor numérico de ponto flutuante.
     */
    public double Comprimento() {
        double total = 0;

        for (int i = 1; i < pontos.size(); i++) {
            total += pontos.get(i - 1).distanciaPara(pontos.get(i));
        }

        return total;
    }

    /**
     * Calcula os pontos de interseção entre uma linha definida por um segmento de reta
     * fornecido e os segmentos consecutivos que compõem a rota.
     *
     * @param sr O segmento de reta usado para calcular as interseções com os segmentos
     *           da rota.
     * @return Uma lista de objetos {@code Ponto} representando os pontos de interseção
     * entre o segmento fornecido e a rota. Retorna {@code null} se não houver
     * pontos de interseção.
     */
    public List<Ponto> Intersect(SegmentoReta sr) {
        ArrayList<Ponto> intersecoes = new ArrayList<>();

        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta segmentoRota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));

            Ponto p = segmentoRota.intersect(sr);
            if (p != null)
                intersecoes.add(p);
        }

        return (intersecoes.isEmpty()) ? null : intersecoes;
    }
}
