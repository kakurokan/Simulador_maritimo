/**
 * Representa um triângulo definido por três segmentos de reta que conectam três pontos no espaço.
 * O triângulo é formado apenas se os três pontos fornecidos não forem colineares.
 * Caso contrário, o programa será encerrado.
 *
 * @author Léo Souza
 * @version 12/03/26
 * @inv os vértices não podem ser colineares
 */
public class Triangulo {
    private SegmentoReta[] lados;

    /**
     * Constrói um triângulo a partir de um array de pontos fornecido.
     * O triângulo é definido por exatamente três pontos que não são colineares.
     * Caso as condições de construção não sejam atendidas, o programa será encerrado.
     *
     * @param pontos Um array de objetos da classe Ponto representando os vértices do triângulo.
     *               O array deve conter exatamente três pontos e os pontos não podem ser colineares.
     *               Caso contrário, o programa imprimirá "Triangulo:iv" e será encerrado.
     */
    public Triangulo(Ponto[] pontos) {
        if (pontos.length != 3) {
            IO.println("Triangulo:iv");
            System.exit(0);
        }

        Vetor ab = new Vetor(pontos[0], pontos[1]);
        Vetor ac = new Vetor(pontos[0], pontos[2]);

        if (Math.abs(ab.produtoVetorial(ac)) < Ponto.eps) {
            IO.println("Triangulo:iv");
            System.exit(0);
        }

        lados = new SegmentoReta[3];

        for (int i = 0; i < pontos.length; i++) {
            lados[i] = new SegmentoReta(pontos[i], pontos[(i + 1) % 3]);
        }
    }
}
