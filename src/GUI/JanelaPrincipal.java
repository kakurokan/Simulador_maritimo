package GUI;

import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class JanelaPrincipal extends JFrame {
    private Timer timer;
    private PainelMapa painel;
    private Simulador simulador;

    public JanelaPrincipal(Simulador simulador,
                           List<Route> rotas,
                           List<Poligono> obstaculos,
                           List<Tempestade> tempestades,
                           Map<String, Ponto> posicoesPortos) {

        this.simulador = simulador;
        this.painel = new PainelMapa(rotas, tempestades, obstaculos, posicoesPortos);

        setTitle("Simulador marítimo");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(painel, BorderLayout.CENTER);

        this.timer = new Timer(16, e -> {
            simulador.atualizar(0.016);
            painel.atualizarSnapshot(simulador.gerarSnapshot());
            painel.repaint();
        });
    }

    public void iniciar() {
        setVisible(true);
        timer.start();
    }
}
