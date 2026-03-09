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
        ArrayList<SegmentoReta> segmentos = new ArrayList<>();

        double xa, ya, xb, yb;
        Ponto a, b;

        for (int i = 3; i < parts.length; i += 2) {
            xa = Double.parseDouble(parts[i - 3]);
            ya = Double.parseDouble(parts[i - 2]);

            xb = Double.parseDouble(parts[i - 1]);
            yb = Double.parseDouble(parts[i]);

            a = new Ponto(xa, ya);
            b = new Ponto(xb, yb);

            SegmentoReta seg = new SegmentoReta(a, b);
            segmentos.add(seg);
        }

        Route rota = new Route(segmentos);

        line = br.readLine();
        parts = line.split(" ");

        xa = Double.parseDouble(parts[0]);
        ya = Double.parseDouble(parts[1]);
        xb = Double.parseDouble(parts[2]);
        yb = Double.parseDouble(parts[3]);

        a = new Ponto(xa, ya);
        b = new Ponto(xb, yb);

        SegmentoReta seg = new SegmentoReta(a, b);

        List<Ponto> intersecoes = rota.Intersect(seg);

        IO.println(rota.Comprimento());

        for (Ponto p : intersecoes) {
            IO.println(p);
        }
    }
}
