import java.util.List;

/**
 * A classe abstrata {@code FiguraGeometrica} define a estrutura base para representar
 * figuras geométricas no plano cartesiano. Ela serve como uma superclasse para outros
 * tipos específicos de figuras geométricas, como polígonos ou círculos.
 * <p>
 * Esta classe fornece uma base comum para abstrair propriedades e comportamentos
 * compartilhados entre diferentes tipos de figuras geométricas.
 * <p>
 * Classes que estendem {@code FiguraGeometrica} devem implementar comportamentos
 * específicos, como cálculo de área, perímetro ou outras características próprias
 * de cada figura geométrica.
 *
 * @author Léo Souza
 * @version 13/03/26
 */
public abstract class Obstaculo {
    public abstract List<Ponto> intersect(Route rota);
}
