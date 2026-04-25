package Engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Classe principal que processa dados de navegação e calcula comprimento de rota,
 * tempo de viagem, posição num dado tempo e velocidades por segmento.
 *
 * @author Léo Souza
 * @version 24/03/26
 */
public class Cliente {
    /**
     * Lê dados de entrada do utilizador para realizar cálculos relacionados à navegação.
     * Processa coordenadas da rota, velocidade do vento, velocidade linear e tempo.
     * Exibe o comprimento da rota, tempo de viagem, posição num dado tempo,
     * e velocidades para cada segmento vetorial da rota considerando a velocidade do vento.
     *
     * @throws IOException se ocorrer um erro ao ler os dados de entrada.
     */
    void main() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] parts = line.split(" ");
        double[] coordenadas = new double[parts.length];

        for (int i = 0; i < coordenadas.length; i++) {
            coordenadas[i] = Double.parseDouble(parts[i]);
        }

        line = br.readLine();
        parts = line.split(" ");
        Vetor windSpeed = new Vetor(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));

        line = br.readLine();
        double linearSpeed = Double.parseDouble(line);

        line = br.readLine();
        double time = Double.parseDouble(line);

        Route rota = new Route(coordenadas);
        IO.println(String.format("%.2f", rota.Comprimento()));

        Navegante navegante = new Navegante(rota);

        IO.println(String.format("%.2f", navegante.tempoParaPercorrer(linearSpeed)));

        IO.println(navegante.posicao(linearSpeed, time));

        List<Vetor> velocidades = navegante.velocidadePorSegmento(windSpeed, linearSpeed);
        int size = velocidades.size() - 1;
        for (int i = 0; i <= size; i++) {
            if (i != size) {
                IO.print(velocidades.get(i) + " ");
            } else {
                IO.print(velocidades.get(i) + "\n");
            }
        }
    }
}