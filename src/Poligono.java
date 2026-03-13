/**
 * @author Léo Souza
 * @version 09/02/26
 */
public abstract class Poligono {
    private final Ponto[] vertices;

    public Poligono(Ponto[] pontos) {
        this.vertices = pontos;
    }

    public Ponto[] getVertices() {
        return vertices;
    }

    public SegmentoReta[] lados() {
        SegmentoReta[] lados = new SegmentoReta[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            lados[i] = new SegmentoReta(vertices[i], vertices[(i + 1) % vertices.length]);
        }
        return lados;
    }
}
