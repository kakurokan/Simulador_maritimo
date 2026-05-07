package Engine;

import java.util.List;

/**
 * Representa a interface para objetos que podem funcionar como obstáculos num espaço cartesiano.
 * A interface define um contrato para verificar interseções entre o obstáculo e uma rota composta
 * por segmentos de reta.
 *
 * @author Léo Souza
 * @version 13/03/26
 */
public interface Obstaculo {
    abstract public List<Ponto> intersect(Route rota);
}
