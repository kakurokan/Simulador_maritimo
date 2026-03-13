import java.util.Arrays;

/**
 * @author Léo Souza
 * @version 13/03/26
 * @inv os lados opostos são iguais e as diagonais com o mesmo comprimento
 */
public class Retangulo extends Poligono {
    /**
     * Constrói um Retangulo a partir de um conjunto de pontos dados.
     * O Retangulo é validado garantindo que possui exatamente quatro pontos,
     * que os lados opostos possuem o mesmo comprimento e que as diagonais são iguais.
     * Caso alguma dessas condições não seja atendida, o programa será encerrado.
     *
     * @param pontos Array de objetos do tipo Ponto que representam os vértices do retângulo.
     *               Deve conter exatamente 4 elementos, em ordem que permita formar o retângulo.
     *               Caso contrário, o programa será finalizado indicando estrutura inválida.
     * @pre pontos != null e pontos.length == 4
     * @pos Um array interno {@code lados} é inicializado e preenchido com 4
     * instâncias válidas de {@code SegmentoReta} que conectam os pontos adjacentes fornecidos.
     */
    public Retangulo(Ponto[] pontos) {
        super(Arrays.copyOf(pontos, pontos.length));

        if (pontos.length != 4) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }

        SegmentoReta[] lados = this.lados();

        //Verifica se os lados opostos são iguais
        if (lados[0].comprimentoDiferente(lados[2]) || lados[1].comprimentoDiferente(lados[3])) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }

        SegmentoReta diagonal1 = new SegmentoReta(pontos[0], pontos[2]);
        SegmentoReta diagonal2 = new SegmentoReta(pontos[1], pontos[3]);

        //Verifica se as diagonais são iguais
        if (diagonal1.comprimentoDiferente(diagonal2)) {
            IO.println("Retangulo:iv");
            System.exit(0);
        }
    }
}
