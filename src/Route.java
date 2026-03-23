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
 * @inv Uma rota tem pelo menos dois pontos e quantidade de coordenadas deve ser par.
 */
public class Route {
    private final List<Ponto> pontos;

    /**
     * Constrói um objeto {@code Route} com base numa lista de pontos fornecida.
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
     * Constrói um objeto {@code Route} com base em um array de coordenadas
     * fornecido. Cada par de valores do array (x, y) representa as
     * coordenadas de um ponto que compõe a rota. A rota é imutável e
     * precisa conter pelo menos dois pontos para ser válida.
     *
     * @param coordenadas Um array de valores do tipo {@code double},
     *                    onde cada par consecutivo (x, y) representa
     *                    as coordenadas de um ponto da rota.
     *                    O array deve ter um número par de elementos.
     *                    Caso contrário, o programa será encerrado com um erro.
     * @pre coordenadas != null
     * @pos Uma lista com pontos criados a partir das coordenadas é guardada no objeto.
     */
    public Route(double[] coordenadas) {
        if (coordenadas.length % 2 != 0) {
            IO.println("Rota:iv");
            System.exit(0);
        }

        pontos = new ArrayList<>();
        for (int i = 1; i < coordenadas.length; i += 2) {
            pontos.add(new Ponto(coordenadas[i - 1], coordenadas[i]));
        }
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
     * Calcula os pontos de interseção entre a rota atual, definida por uma sequência de
     * segmentos de reta, e um polígono fornecido como parâmetro. A rota é representada
     * pela sequência de pontos armazenados na classe, e os segmentos consecutivos formam
     * os lados da rota, que são comparados aos lados do polígono para identificar as
     * interseções.
     *
     * @param poligono Um objeto {@code Poligono} representando o polígono com o qual os
     *                 pontos de interseção serão calculados. Deve ser uma instância válida
     *                 e não nula de {@code Poligono}.
     * @return Uma lista de objetos {@code Ponto} representando os pontos de interseção
     * entre a rota e o polígono fornecido, ou {@code null} caso não existam interseções.
     * A lista não conterá pontos duplicados.
     * @pre poligono != null
     */
    public List<Ponto> Intersect(Poligono poligono) {
        ArrayList<Ponto> intersecoes = new ArrayList<>();

        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta segmentoRota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));
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
        }

        return (intersecoes.isEmpty()) ? null : intersecoes;
    }

    /**
     * Calcula os pontos de interseção entre a rota atual, representada por uma sequência de
     * segmentos de reta, e um círculo fornecido como parâmetro. A rota é definida pela
     * sequência de pontos armazenados na classe, formando segmentos consecutivos, e é comparada
     * ao círculo para identificar os pontos de interseção.
     *
     * @param circulo Um objeto {@code Circulo} representando o círculo com o qual os pontos
     *                de interseção serão calculados. Deve ser uma instância válida e não nula
     *                de {@code Circulo}.
     * @return Uma lista de objetos {@code Ponto} representando os pontos de interseção entre
     * a rota e o círculo fornecido, ou {@code null} caso não existam interseções.
     * A lista não conterá pontos duplicados.
     * @pre circulo != null
     */
    public List<Ponto> Intersect(Circulo circulo) {
        ArrayList<Ponto> intersecoes = new ArrayList<>();

        for (int i = 1; i < pontos.size(); i++) {
            SegmentoReta segmentoRota = new SegmentoReta(pontos.get(i - 1), pontos.get(i));

            List<Ponto> pontosIntersecao = segmentoRota.intersect(circulo);

            for (Ponto intersecao : pontosIntersecao) {
                if (!intersecoes.contains(intersecao)) {
                    intersecoes.add(intersecao);
                }
            }
        }

        return (intersecoes.isEmpty()) ? null : intersecoes;
    }

    /**
     * Calcula os pontos de interseção entre a rota atual, representada por uma sequência de
     * segmentos de reta, e o segmento de reta fornecido como parâmetro.
     *
     * @param sr O segmento de reta com o qual se deseja calcular os pontos de interseção.
     *           Deve ser uma instância válida de {@code SegmentoReta}.
     * @return Uma lista de objetos {@code Ponto} representando os pontos de interseção
     * encontrados entre a rota e o segmento de reta fornecido, ou {@code null}
     * caso não haja interseções.
     * @pre sr != null
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
