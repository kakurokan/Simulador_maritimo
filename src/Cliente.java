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
 * @version 09/03/26
 */
public class Cliente {


    /**
     * Método principal que lê dados de entrada, cria uma rota a partir de pontos,
     * calcula o comprimento da rota e identifica interseções com um segmento de reta.
     *
     * <p>A primeira linha de entrada contém coordenadas de pontos (x y x y ...) que formam a rota.
     * A segunda linha contém quatro valores (xa ya xb yb) que definem um segmento de reta.</p>
     *
     * <p>O método imprime:
     * <ul>
     *   <li>O comprimento total da rota formatado com duas casas decimais</li>
     *   <li>Os pontos de interseção entre a rota e o segmento, ou "null" se não houver interseções</li>
     * </ul>
     * </p>
     *
     * @throws IOException se ocorrer um erro durante a leitura da entrada padrão
     */
    public static void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] parts = line.split(" ");
        ArrayList<Ponto> pontos = new ArrayList<>();

        double xa, ya, xb, yb;
        Ponto a, b;

        for (int i = 1; i < parts.length; i += 2) {
            xa = Double.parseDouble(parts[i - 1]);
            ya = Double.parseDouble(parts[i]);

            a = new Ponto(xa, ya);
            pontos.add(a);
        }

        Route rota = new Route(pontos);

        line = br.readLine();
        parts = line.split(" ");

        xa = Double.parseDouble(parts[0]);
        ya = Double.parseDouble(parts[1]);
        xb = Double.parseDouble(parts[2]);
        yb = Double.parseDouble(parts[3]);

        a = new Ponto(xa, ya);
        b = new Ponto(xb, yb);

        IO.println(String.format("%.2f", rota.Comprimento()));

        SegmentoReta seg = new SegmentoReta(a, b);
        List<Ponto> intersecoes = rota.Intersect(seg);

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
}
