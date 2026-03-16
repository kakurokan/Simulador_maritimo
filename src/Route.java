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
 * @inv Uma rota tem pelo menos dois pontos e dois pontos consecutivos devem ser diferentes.
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
     * Calcula os pontos de interseção entre a rota atual e uma figura geométrica fornecida.
     * A rota é definida pela sequência de segmentos de reta formada pelos pontos internos da classe.
     * Dependendo do tipo da figura geométrica fornecida (polígono, círculo ou segmento de reta),
     * os pontos de interseção são determinados e armazenados em uma lista.
     *
     * @param obstaculo A figura geométrica com a qual os pontos de interseção serão calculados.
     *                  Pode ser uma instância de {@code Poligono}, {@code Circulo} ou {@code SegmentoReta}.
     * @return Uma lista de {@code Ponto} representando os pontos de interseção,
     * ou {@code null} se nenhum ponto de interseção for encontrado.
     * @pre obstaculo != null
     * @pos A rota e a figura permanecem inalterados
     */
    public List<Ponto> Intersect(FiguraGeometrica obstaculo) {
        ArrayList<Ponto> intersecoes = new ArrayList<>();

        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta segmentoRota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));

            if (obstaculo instanceof Poligono poligono) {
                SegmentoReta[] lados = poligono.getLados();

                for (SegmentoReta lado : lados) {
                    if (lado != null) {
                        Ponto intersecao = segmentoRota.intersect(lado);
                        if (intersecao != null) {
                            if (!intersecoes.contains(intersecao)) {
                                intersecoes.add(intersecao);
                            }
                        }
                    }
                }
            } else if (obstaculo instanceof Circulo circulo) {
                List<Ponto> pontosIntersecao = segmentoRota.intersect(circulo);

                for (Ponto intersecao : pontosIntersecao) {
                    if (!intersecoes.contains(intersecao)) {
                        intersecoes.add(intersecao);
                    }
                }
            } else if (obstaculo instanceof SegmentoReta sr) {
                Ponto p = segmentoRota.intersect(sr);
                if (p != null)
                    intersecoes.add(p);
            }
        }

        return (intersecoes.isEmpty()) ? null : intersecoes;
    }
}
