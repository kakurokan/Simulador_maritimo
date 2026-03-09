import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Léo Souza
 * @version 09/03/26
 */
public class Cliente {

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
