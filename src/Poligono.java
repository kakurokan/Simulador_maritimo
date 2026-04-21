import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A classe abstrata {@code Poligono} define uma estrutura básica para representar polígonos
 * no plano cartesiano. Um polígono é descrito como uma sequência de vértices conectados
 * consecutivamente, formando uma figura fechada.
 * <p>
 * Esta classe fornece propriedades e comportamentos fundamentais que podem ser estendidos
 * por classes derivadas para representar tipos específicos de polígonos, como triângulos,
 * quadrados e retângulos.
 *
 * @author Léo Souza
 * @version 13/03/26
 * @inv Tem de ter pelo menos três vértices, definidos numa lista ordenada no sentido dos ponteiros do relógio.
 */
public class Poligono extends ObstaculoEstatico {
    private final Ponto[] vertices;

    /**
     * Constrói um polígono com os vértices especificados.
     * O polígono é definido por uma sequência de pontos no plano cartesiano,
     * que representam os vértices conectados consecutivamente.
     *
     * @param pontos Um array de objetos {@code Ponto} que representam os vértices do polígono.
     *               Deve conter pelo menos três pontos para formar um polígono válido.
     * @throws IllegalArgumentException se o array {@code pontos} contiver
     *                                  menos do que três pontos.
     * @pre pontos != null e os pontos devem estar ordenados na ordem do ponteiro do relógio
     * @pos getVertices() == uma cópia de pontos
     */
    public Poligono(Ponto[] pontos) {
        if (pontos.length < 3) {
            throw new IllegalArgumentException("Poligono:iv");
        }

        this.vertices = Arrays.copyOf(pontos, pontos.length);
    }

    /**
     * Verifica a interseção entre os lados do polígono atual e os segmentos de reta
     * da rota fornecida, retornando uma lista de pontos onde essas interseções ocorrem.
     *
     * @param rota A rota (instância de {@code Route}) que contém os segmentos de reta
     *             a serem verificados para interseção com os lados do polígono.
     * @return Uma lista de pontos ({@code List<Ponto>}) que representam as interseções entre
     * os lados do polígono e os segmentos de reta da rota. Retorna {@code null} se
     * não houver interseções.
     * @pre rota != null
     */
    @Override
    public List<Ponto> intersect(Route rota) {
        ArrayList<Ponto> intersecoes = new ArrayList<>();
        List<SegmentoReta> segmentosRota = rota.getSegmentos();

        for (SegmentoReta segmentoRota : segmentosRota) {
            for (int i = 0; i < vertices.length; i++) {
                SegmentoReta lado = new SegmentoReta(vertices[i], vertices[(i + 1) % vertices.length]);
                Ponto intersecao = segmentoRota.intersect(lado);
                if (intersecao != null) {
                    if (!intersecoes.contains(intersecao)) {
                        intersecoes.add(intersecao);
                    }
                }
            }
        }

        return (intersecoes.isEmpty()) ? null : intersecoes;
    }

}
