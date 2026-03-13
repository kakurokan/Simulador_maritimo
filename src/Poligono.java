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
 */
public abstract class Poligono {
    private final Ponto[] vertices;

    /**
     * Constrói um polígono com os vértices especificados.
     * O polígono é definido por uma sequência de pontos no plano cartesiano,
     * que representam os vértices conectados consecutivamente.
     *
     * @param pontos Um array de objetos {@code Ponto} que representam os vértices do polígono.
     *               Deve conter pelo menos três pontos para formar um polígono válido.
     * @throws IllegalArgumentException se o array {@code pontos} for nulo ou contiver
     *                                  menos do que três pontos.
     * @pre pontos != null
     * @pos getVertices() == pontos
     */
    public Poligono(Ponto[] pontos) {
        this.vertices = pontos;
    }

    /**
     * Retorna o array de vértices que define o polígono.
     * O array contém os objetos {@code Ponto} que representam as posições dos vértices
     * em um plano cartesiano. A ordem dos elementos no array reflete a sequência
     * em que os vértices estão conectados ao formar o polígono.
     *
     * @return Um array de objetos {@code Ponto} representando os vértices do polígono.
     * O array nunca é nulo e contém pelo menos três elementos.
     */
    public Ponto[] getVertices() {
        return vertices;
    }

    /**
     * Retorna os lados do polígono representados como segmentos de reta. Cada lado é definido
     * pelos vértices consecutivos do polígono, com o último vértice conectado ao primeiro.
     *
     * @return Um array de objetos {@code SegmentoReta}, onde cada elemento representa um lado
     * do polígono. A ordem dos segmentos corresponde à sequência dos vértices no
     * array de vértices do polígono.
     * @pos O objeto não é alterado
     */
    public SegmentoReta[] lados() {
        SegmentoReta[] lados = new SegmentoReta[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            lados[i] = new SegmentoReta(vertices[i], vertices[(i + 1) % vertices.length]);
        }
        return lados;
    }
}
