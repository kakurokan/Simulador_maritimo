package GUI;

import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class JanelaPrincipal extends JFrame {
    private final Timer timer;
    private final PainelMapa painel;
    private final Runnable recriarBarcos;

    public JanelaPrincipal(Simulador simulador,
                           List<Route> rotas,
                           List<Poligono> obstaculos,
                           List<Tempestade> tempestades,
                           Map<String, Ponto> posicoesPortos,
                           Vetor velocidadeCorrente,
                           Runnable recriarBarcos) {
        this.recriarBarcos = recriarBarcos;
        this.painel = new PainelMapa(rotas, tempestades, obstaculos, posicoesPortos, velocidadeCorrente);

        setTitle("Simulador marítimo");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(painel, BorderLayout.CENTER);

        JPanel painelSul = new JPanel();

        AcaoReiniciarSimulacao reset = new AcaoReiniciarSimulacao(simulador, painel, this.recriarBarcos);

        JButton botaoReset = new JButton(reset);

        painelSul.add(botaoReset);
        add(painelSul, BorderLayout.SOUTH);

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
