package GUI;

import Engine.Ponto;
import Engine.SnapshotSimulacao;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PainelMapa extends JPanel {
    private SnapshotSimulacao snapshot;

    public PainelMapa() {
        setBackground(new Color(150, 230, 255));
    }

    public void atualizarSnapshot(SnapshotSimulacao snapshotSimulacao) {
        this.snapshot = snapshotSimulacao;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (snapshot == null) return;

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics2D.setColor(Color.darkGray);

        List<Ponto> posicoesNavios = snapshot.getPosicoesNavios();
        for (Ponto navio : posicoesNavios) {
            int x = (int) navio.getX();
            int y = (int) navio.getY();

            graphics2D.fillOval(x - 5, y - 5, 10, 10);
        }

        graphics2D.setColor(Color.BLACK);
        int yInfo = 20;
        Map<String, List<SnapshotSimulacao.NavioEmEspera>> filaPorPorto = snapshot.getNaviosEmEsperaPorPorto();

        for (Map.Entry<String, List<SnapshotSimulacao.NavioEmEspera>> entry : filaPorPorto.entrySet()) {
            for (SnapshotSimulacao.NavioEmEspera navioEmEspera : entry.getValue()) {
                graphics2D.drawString(navioEmEspera.toString(), 10, yInfo);
                yInfo += 15;
            }
        }
    }
}
