package Engine;

/**
 * A classe Engine.Retangulo representa um polígono específico de quatro lados no plano cartesiano,
 * onde os lados opostos possuem o mesmo comprimento e as diagonais têm o mesmo tamanho.
 * Essa classe estende a classe Engine.Poligono, herdando as suas propriedades e métodos básicos.
 *
 * @author Léo Souza
 * @version 13/03/26
 * @inv os lados opostos são iguais e as diagonais com o mesmo comprimento
 */
public class Retangulo extends Poligono {
    /**
     * Constrói um Engine.Retangulo a partir de um conjunto de pontos dados.
     * O Engine.Retangulo é validado garantindo que possui exatamente quatro pontos,
     * que os lados opostos possuem o mesmo comprimento e que as diagonais são iguais.
     * Caso alguma dessas condições não seja atendida, será lançada uma IllegalArgumentException.
     *
     * @param pontos Array de objetos do tipo Engine.Ponto que representam os vértices do retângulo.
     *               Deve conter exatamente 4 elementos, em ordem que permita formar o retângulo.
     *               Caso contrário, será lançada uma IllegalArgumentException indicando estrutura inválida.
     * @throws IllegalArgumentException Se o array não contiver exatamente quatro pontos,
     *                                  os lados opostos não tiverem o mesmo comprimento ou as diagonais não forem iguais
     * @pre pontos != null e pontos.length == 4
     * @pos Uma cópia do array pontos é guardada no objeto
     */
    public Retangulo(Ponto[] pontos) {
        super(pontos);

        if (pontos.length != 4) {
            throw new IllegalArgumentException("Engine.Retangulo:iv");
        }

        //Verifica se os lados opostos são iguais
        if (!(Math.abs(pontos[0].distanciaPara(pontos[1]) - pontos[2].distanciaPara(pontos[3])) < Ponto.eps)
                || !(Math.abs(pontos[1].distanciaPara(pontos[2]) - pontos[0].distanciaPara(pontos[3])) < Ponto.eps)) {
            throw new IllegalArgumentException("Engine.Retangulo:iv");
        }

        //Verifica se as diagonais são iguais
        if (!(Math.abs(pontos[0].distanciaPara(pontos[2]) - pontos[1].distanciaPara(pontos[3])) < Ponto.eps)) {
            throw new IllegalArgumentException("Engine.Retangulo:iv");
        }
    }
}
