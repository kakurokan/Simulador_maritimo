package GUI;

import Engine.Simulador;

import javax.swing.*;
import java.awt.*;

public class JanelaPrincipal extends JFrame {
    private Timer timer;
    private PainelMapa painel;
    private Simulador simulador;

    public JanelaPrincipal(Simulador simulador) {
        this.simulador = simulador;
        this.painel = new PainelMapa();

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
