/**
 * @author Léo Souza
 * @version 12/03/26
 * @inv os vértices não podem ser colineares?
 */
public class Triangulo {
    private SegmentoReta[] lados;

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
