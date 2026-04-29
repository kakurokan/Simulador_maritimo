import Engine.*;
import GUI.JanelaPrincipal;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        List<Route> rotas = Arrays.asList(
                new Route(Arrays.asList(
                        new Ponto(100, 100),
                        new Ponto(400, 200)
                )),
                new Route(Arrays.asList(
                        new Ponto(400, 200),
                        new Ponto(300, 400)
                )),
                new Route(Arrays.asList(
                        new Ponto(300, 400),
                        new Ponto(100, 100)
                ))
        );

        List<Obstaculo> obstaculos = new ArrayList<>();

        TorreDeControlo torre = new GestorMaritimo(rotas, obstaculos);

        Porto porto1 = new Porto("Porto A", new Ponto(100, 100), torre);
        Porto porto2 = new Porto("Porto B", new Ponto(400, 200), torre);
        Porto porto3 = new Porto("Porto C", new Ponto(300, 400), torre);

        List<Porto> portos = Arrays.asList(porto1, porto2, porto3);

        Simulador simulador = new Simulador(
                new Vetor(1, 0),
                rotas,
                portos,
                obstaculos
        );

        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal gui = new JanelaPrincipal(simulador);
            gui.iniciar();
        });
    }
}
