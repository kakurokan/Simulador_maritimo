package Engine;

/**
 * Representa um quadrado no plano cartesiano, sendo uma extensão de {@code Engine.Poligono}.
 * Um quadrado é definido por quatro vértices sequenciais.
 *
 * @author Léo Souza
 * @version 12/03/26
 * @inv os lados devem ser iguais, as diagonais devem ter o mesmo comprimento e deve ter exatamente quatro lados
 */
public class Quadrado extends Poligono {
    /**
     * Constrói um objeto da classe Engine.Quadrado verificando a validade dos pontos fornecidos.
     * O objeto será considerado válido se atender às seguintes condições:
     * - Exatamente quatro pontos são fornecidos.
     * - Todos os lados formados pelos pontos são iguais.
     * - As diagonais formadas pelos pontos têm o mesmo comprimento.
     * Caso qualquer uma dessas condições não seja satisfeita, será lançada uma IllegalArgumentException.
     *
     * @param pontos Um array de objetos da classe Engine.Ponto representando os vértices do quadrado.
     *               O array deve conter exatamente quatro pontos dispostos em sequência.
     *               Caso contrário, será lançada uma IllegalArgumentException.
     * @throws IllegalArgumentException Se o array não contiver exatamente quatro pontos,
     *                                  os lados não forem todos iguais ou as diagonais não tiverem o mesmo comprimento
     * @pre pontos != null e pontos.length == 4
     * @pos Uma cópia do array pontos é guardada no objeto
     */
    public Quadrado(Ponto[] pontos) {
        super(pontos);

        if (pontos.length != 4) {
            throw new IllegalArgumentException("Engine.Quadrado:iv");
        }

        double comprimentoLado0 = pontos[0].distanciaPara(pontos[1]);

        //Verifica se todos os lados são iguais
        for (int i = 1; i < 4; i++) {
            if (!(Math.abs(comprimentoLado0 - pontos[i].distanciaPara(pontos[(i + 1) % 4])) < Ponto.eps)) {
                throw new IllegalArgumentException("Engine.Quadrado:iv");
            }
        }

        //Verifica se as diagonais são iguais
        if (!(Math.abs(pontos[0].distanciaPara(pontos[2]) - pontos[1].distanciaPara(pontos[3])) < Ponto.eps)) {
            throw new IllegalArgumentException("Engine.Quadrado:iv");
        }
    }
}
