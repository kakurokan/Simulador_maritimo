import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma rota no espaço 2D definida por uma sequência de pontos.
 * Os pontos na lista fornecem a ordem na qual a rota é seguida,
 * e ela pode conter múltiplos segmentos consecutivos formados entre pares de pontos.
 * A classe é imutável.
 *
 * @author Léo Souza
 * @version 09/03/26
 * @inv A rota deve ter pelo menos dois pontos
 */
public class Route {
    private final List<Ponto> pontos;

    /**
     * Constrói um objeto {@code Route} com base em uma lista de pontos fornecida.
     * A rota é imutável e representada pela sequência de pontos especificada.
     *
     * @param pontos A lista de {@code Ponto} que define a sequência de pontos da rota.
     *               Deve conter pelo menos dois pontos, e dois pontos consecutivos
     *               na rota devem ser diferentes.
     * @pre pontos != null e pontos.size() >= 2
     * @pos Uma cópia da lista pontos é armazenada internamente
     */
    public Route(List<Ponto> pontos) {
        this.pontos = List.copyOf(pontos);
    }

    /**
     * Calcula o comprimento total da rota representada pelo conjunto de pontos.
     * O comprimento é a soma das distâncias entre pares consecutivos de pontos
     * na sequência de pontos que compõem a rota.
     *
     * @return O comprimento total da rota como um valor numérico de ponto flutuante.
     * @pos O valor retornado é ≥ 0.0.
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
     * @pre sr != null
     * @pos O estado interno dos objetos não é alterado.
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
