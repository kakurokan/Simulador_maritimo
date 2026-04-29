package GUI;

import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainelMapa extends JPanel {
    private final List<Route> rotas;
    private final List<Tempestade> tempestades;
    private final List<Poligono> obstaculos;
    private final Map<String, Ponto> posicoesPortos;

    private final Map<Ponto, Character> nomePontos = new HashMap<>();
    private final int escala = 50;
    private final int offsetX = 50;
    private final int offsetY = 50;
    private final Color[] coresRotas = {Color.RED, Color.MAGENTA, Color.ORANGE, new Color(0, 150, 0), Color.PINK};
    private SnapshotSimulacao snapshot;


    public PainelMapa(List<Route> rotas, List<Tempestade> tempestades, List<Poligono> obstaculosEstaticos, Map<String, Ponto> posicoesPortos) {
        this.rotas = rotas;
        this.tempestades = tempestades;
        this.obstaculos = obstaculosEstaticos;
        this.posicoesPortos = posicoesPortos;

        setBackground(Color.white);
        atribuirLetrasAosPontos();
    }

    private int telaX(double x) {
        return offsetX + (int) (x * escala);
    }

    private int telaY(double y) {
        return getHeight() - offsetY - (int) (y * escala);
    }

    private void atribuirLetrasAosPontos() {
        char letraAtual = 'A';

        for (Route r : rotas) {
            for (SegmentoReta seg : r.getSegmentos()) {
                if (!nomePontos.containsKey(seg.getA()))
                    nomePontos.put(seg.getA(), letraAtual++);
                if (!nomePontos.containsKey(seg.getB()))
                    nomePontos.put(seg.getB(), letraAtual++);
            }
        }

        for (Poligono poligono : obstaculos) {
            for (Ponto p : poligono.getVertices()) {
                if (!nomePontos.containsKey(p))
                    nomePontos.put(p, letraAtual++);
            }
        }
    }

    public void atualizarSnapshot(SnapshotSimulacao snapshotSimulacao) {
        this.snapshot = snapshotSimulacao;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        desenharEixos(graphics2D);
        desenharObstaculos(graphics2D);
        desenharTempestades(graphics2D);
        desenharRotasEPontos(graphics2D);

        if (snapshot != null) {
            desenharNavios(graphics2D);
            desenharCaixasFlutuantesPorto(graphics2D);
        }
    }

    private void desenharEixos(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        int origemX = telaX(0);
        int origemY = telaY(0);

        g.drawLine(origemX, origemY, origemX, 20);
        g.drawString("Y", origemX - 15, 20);

        g.drawLine(origemX, origemY, getWidth() - 20, origemY);
        g.drawString("X", getWidth() - 20, origemY + 15);
    }

    private void desenharTempestades(Graphics2D g) {
        g.setColor(new Color(44, 136, 227, 80));
        for (Tempestade tempestade : tempestades) {
            Circulo c = tempestade.getArea();
            int r = (int) (c.getRaio() * escala);
            int cx = telaX(c.getCentro().getX());
            int cy = telaY(c.getCentro().getY());
            g.fillOval(cx - r, cy - r, r * 2, r * 2);
        }
    }

    private void desenharRotasEPontos(Graphics2D g) {
        int corIndex = 0;

        g.setStroke(new BasicStroke(2));
        for (Route rota : rotas) {
            g.setColor(coresRotas[corIndex % coresRotas.length]);
            for (SegmentoReta seg : rota.getSegmentos()) {
                g.drawLine(telaX(seg.getA().getX()), telaY(seg.getA().getY()),
                        telaX(seg.getB().getX()), telaY(seg.getB().getY()));
            }
            corIndex++;
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        for (Map.Entry<Ponto, Character> entry : nomePontos.entrySet()) {
            Ponto p = entry.getKey();
            int x = telaX(p.getX());
            int y = telaY(p.getY());

            g.fillOval(x - 3, y - 3, 6, 6);
            g.drawString(String.valueOf(entry.getValue()), x + 8, y - 8);
        }
    }

    private void desenharNavios(Graphics2D g) {
        g.setColor(Color.BLACK);
        for (Ponto p : snapshot.getPosicoesNavios()) {
            int x = telaX(p.getX());
            int y = telaY(p.getY());

            g.fillOval(x - 6, y - 6, 12, 12);
        }
    }

    private void desenharCaixasFlutuantesPorto(Graphics2D g) {
        g.setFont(new Font("Arial", Font.PLAIN, 11));

        for (Map.Entry<String, Ponto> entry : posicoesPortos.entrySet()) {
            String nomePorto = entry.getKey();
            Ponto posicao = entry.getValue();

            int cx = telaX(posicao.getX());
            int cy = telaY(posicao.getY());

            List<SnapshotSimulacao.NavioEmEspera> emEspera = snapshot.getNaviosEmEsperaPorPorto().getOrDefault(nomePorto, new ArrayList<>());

            List<String> linhasTexto = new ArrayList<>();
            linhasTexto.add("[" + nomePorto + " (" + nomePontos.get(posicao) + ")]");
            if (emEspera.isEmpty()) {
                linhasTexto.add("Nenhum navio em espera");
            } else {
                for (SnapshotSimulacao.NavioEmEspera info : emEspera) {
                    linhasTexto.add(info.toString());
                }
            }

            int larguraCaixa = 180;
            int alturaCaixa = (linhasTexto.size() * 15) + 10;
            int boxX = cx + 15; // Deslocado um pouco para a direita
            int boxY = cy - (alturaCaixa / 2); // Centrado verticalmente com o porto

            g.setColor(new Color(255, 255, 255, 220)); // Branco ligeiramente transparente
            g.fillRect(boxX, boxY, larguraCaixa, alturaCaixa);
            g.setColor(Color.BLACK);
            g.drawRect(boxX, boxY, larguraCaixa, alturaCaixa);

            int textY = boxY + 15;
            for (String linha : linhasTexto) {
                g.drawString(linha, boxX + 5, textY);
                textY += 15;
            }
        }
    }

    private void desenharObstaculos(Graphics2D g) {
        Color bege = new Color(245, 245, 220);

        for (Poligono poligono : obstaculos) {
            Ponto[] vertices = poligono.getVertices();
            int nVertices = vertices.length;

            int[] xVertices = new int[nVertices];
            int[] yVertices = new int[nVertices];

            for (int i = 0; i < nVertices; i++) {
                xVertices[i] = telaX(vertices[i].getX());
                yVertices[i] = telaY(vertices[i].getY());
            }

            Polygon shapePoligono = new Polygon(xVertices, yVertices, nVertices);

            g.setColor(bege);
            g.fillPolygon(shapePoligono);

            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(1));
            g.drawPolygon(shapePoligono);
        }
    }
}
