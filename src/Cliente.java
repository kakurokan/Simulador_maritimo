import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Classe que representa o ponto de entrada para execução do programa, lidando
 * com a criação e manipulação de rotas, cálculo de comprimentos e verificação
 * de interseções com segmentos de reta.
 * Essa classe é responsável por:
 * <p>
 * - Ler coordenadas da entrada padrão para formar uma guia de rota composta por pontos.
 * - Calcular o comprimento total da rota gerada.
 * - Identificar e exibir os pontos de interseção da rota com um segmento de reta especificado.
 *
 * @author Léo Souza
 * @version 13/03/26
 */
public class Cliente {


    public static void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] parts = line.split(" ");
        ArrayList<Ponto> pontos;

        double[] coordenadas = arrayStringParaDouble(parts, 0);
        pontos = getPontos(coordenadas);
        Route rota = new Route(pontos);

        line = br.readLine();
        parts = line.split(" ");

        FiguraGeometrica figura = null;
        coordenadas = arrayStringParaDouble(parts, 1);

        switch (parts[0]) {
            case "P":
                pontos = getPontos(coordenadas);
                figura = new Poligono(pontos.toArray(new Ponto[0]));
                break;
            case "S":
                Ponto[] verticesQuadrado = {new Ponto(coordenadas[0], coordenadas[1]),
                        new Ponto(coordenadas[2], coordenadas[3]),
                        new Ponto(coordenadas[4], coordenadas[5]),
                        new Ponto(coordenadas[6], coordenadas[7])};
                figura = new Quadrado(verticesQuadrado);
                break;
            case "R":
                Ponto[] verticesRetangulo = {new Ponto(coordenadas[0], coordenadas[1]),
                        new Ponto(coordenadas[2], coordenadas[3]),
                        new Ponto(coordenadas[4], coordenadas[5]),
                        new Ponto(coordenadas[6], coordenadas[7])};
                figura = new Retangulo(verticesRetangulo);
                break;
            case "T":
                Ponto[] verticesTriangulo = {new Ponto(coordenadas[0], coordenadas[1]),
                        new Ponto(coordenadas[2], coordenadas[3]),
                        new Ponto(coordenadas[4], coordenadas[5])};
                figura = new Triangulo(verticesTriangulo);
                break;
            case "C":
                Ponto centro = new Ponto(coordenadas[0], coordenadas[1]);
                figura = new Circulo(centro, coordenadas[2]);
                break;
            default:
                System.exit(0);
        }

        List<Ponto> intersecoes = rota.Intersect(figura);

        if (intersecoes == null) {
            IO.println("null");
        } else {
            for (int i = 0; i < intersecoes.size(); i++) {
                System.out.print(intersecoes.get(i));

                if (i == intersecoes.size() - 1) {
                    System.out.print("\n");
                } else {
                    System.out.print(" ");
                }
            }
        }
    }

    /**
     * Constrói uma lista de objetos {@code Ponto} baseada em um array de coordenadas.
     * As coordenadas do array são processadas em pares consecutivos para formar os pontos
     * correspondentes no sistema de coordenadas cartesianas.
     *
     * @param coordenadas Um array de números {@code Double} contendo as coordenadas (x, y)
     *                    dos pontos. O tamanho do array deve ser par, contendo pares de
     *                    coordenadas consecutivas.
     * @return Uma lista de objetos {@code Ponto}, contendo os pontos gerados a partir
     * das coordenadas fornecidas.
     */
    private static ArrayList<Ponto> getPontos(double[] coordenadas) {
        ArrayList<Ponto> pontos = new ArrayList<>();

        for (int i = 1; i < coordenadas.length; i += 2) {
            double xa = coordenadas[i - 1];
            double ya = coordenadas[i];

            Ponto a = new Ponto(xa, ya);
            pontos.add(a);
        }

        return pontos;
    }

    /**
     * Converte uma parte de um array de strings num array de doubles, a partir do índice especificado.
     * Cada string na parte especificada do array de entrada é convertida para um {@code double}.
     *
     * @param strings    Um array de strings que contém os valores numéricos a serem convertidos.
     * @param startPoint O índice inicial no array {@code strings} a partir do qual a conversão deve começar.
     *                   Deve estar dentro dos limites do array.
     * @return Um array de doubles criado através da conversão dos valores em string da parte especificada do array de entrada.
     * O tamanho do array resultante é {@code strings.length - startPoint}.
     * @throws NumberFormatException          se alguma string na parte especificada da entrada não puder ser convertida num double válido.
     * @throws ArrayIndexOutOfBoundsException se o {@code startPoint} for menor que 0 ou maior que {@code strings.length}.
     */
    private static double[] arrayStringParaDouble(String[] strings, int startPoint) {
        double[] doubles = new double[strings.length - startPoint];

        for (int i = startPoint; i < strings.length; i++) {
            doubles[i - startPoint] = Double.parseDouble(strings[i]);
        }

        return doubles;
    }
}
