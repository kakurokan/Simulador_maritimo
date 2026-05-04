package GUI;

import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainelMapa extends JPanel {
    private final List<Route> rotas;
    private final List<Poligono> obstaculos;
    private final Map<String, Ponto> posicoesPortos;
    private final Vetor corrente;
    private final Map<Ponto, Character> nomePontos = new HashMap<>();
    private final int escala = 50;
    private final int offsetX = 200;
    private final int offsetY = 50;
    private final Color[] coresRotas = {Color.RED, Color.MAGENTA, Color.ORANGE, new Color(0, 150, 0), Color.PINK};
    private List<Tempestade> tempestades;
    private SnapshotSimulacao snapshot;


    public PainelMapa(List<Route> rotas, List<Tempestade> tempestades, List<Poligono> obstaculosEstaticos, Map<String, Ponto> posicoesPortos, Vetor corrente) {
        this.rotas = rotas;
        this.tempestades = tempestades;
        this.obstaculos = obstaculosEstaticos;
        this.posicoesPortos = posicoesPortos;
        this.corrente = corrente;

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

    public void setTempestades(List<Tempestade> t) {
        this.tempestades = t;
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
        desenharCaixaCorrente(graphics2D);

        if (snapshot != null) {
            desenharNavios(graphics2D);
            desenharCaixasFlutuantesPorto(graphics2D);
            desenharRelogio(graphics2D);
        }
    }

    private void desenharEixos(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        int origemX = telaX(0);
        int origemY = telaY(0);

        //Eixo x
        g.drawLine(origemX, origemY, origemX, 20);
        g.drawString("Y", origemX - 15, 20);

        //Eixo y
        g.drawLine(origemX, origemY, getWidth() - 20, origemY);
        g.drawString("X", getWidth() - 20, origemY + 15);

        //Traços dos eixos
        g.setStroke(new BasicStroke(1));
        g.setFont(new Font("Arial", Font.PLAIN, 10));

        int maxX = (getWidth() - offsetX) / escala;
        int maxY = (getHeight() - offsetY) / escala;

        for (int i = 1; i <= maxX; i++) {
            int posX = telaX(i);
            // Desenha um pequeno traço vertical cruzando o eixo
            g.drawLine(posX, origemY - 4, posX, origemY + 4);
            // Escreve o número
            g.drawString(String.valueOf(i), posX - 3, origemY + 18);
        }

        for (int i = 1; i <= maxY; i++) {
            int posY = telaY(i);
            // Desenha um pequeno traço horizontal cruzando o eixo
            g.drawLine(origemX - 4, posY, origemX + 4, posY);
            // Escreve o número
            g.drawString(String.valueOf(i), origemX - 18, posY + 4);
        }

        // Coloca o 0 na origem
        g.drawString("0", origemX - 12, origemY + 14);
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
        for (SnapshotSimulacao.DadosNavio navio : snapshot.getDadosNavios()) {
            Ponto posAtual = navio.getPosicao();
            Vetor direcao = navio.getDirecao();

            int x = telaX(posAtual.getX());
            int y = telaY(posAtual.getY());

            if (navio.isEmColisao()) {
                int raioNoEcra = (int) (navio.getRaioArea() * escala);

                g.setColor(new Color(255, 0, 0, 60));
                g.fillOval(x - raioNoEcra, y - raioNoEcra, raioNoEcra * 2, raioNoEcra * 2);

                // Borda tracejada vermelha brilhante
                g.setColor(Color.RED);
                Stroke linhaOriginal = g.getStroke();
                g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0));
                g.drawOval(x - raioNoEcra, y - raioNoEcra, raioNoEcra * 2, raioNoEcra * 2);
                g.setStroke(linhaOriginal); // Repor a caneta normal
            }

            double angulo = 0;
            if (direcao != null) {
                angulo = Math.atan2(-direcao.getY(), direcao.getX());
            }

            AffineTransform transformacaoOriginal = g.getTransform();

            g.translate(x, y);
            g.rotate(angulo);

            int[] cascoX = {-10, 6, 12, 6, -10};
            int[] cascoY = {-5, -5, 0, 5, 5};
            g.setColor(Color.DARK_GRAY);
            g.fillPolygon(cascoX, cascoY, 5);

            g.setColor(Color.WHITE);
            g.fillRect(-8, -3, 6, 6);

            g.setColor(Color.BLACK);
            g.drawPolygon(cascoX, cascoY, 5);

            g.setTransform(transformacaoOriginal);
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

            int boxX = cx + 30; // Deslocado um pouco para a direita
            int boxY = cy + 30; // Centrado verticalmente com o porto

            // Se a caixa sair pela direita, atira-a para a esquerda do porto
            if (boxX + larguraCaixa > getWidth() - 10) {
                boxX = cx - larguraCaixa - 30;
            }
            // Se a caixa sair por baixo, atira-a para cima do porto
            if (boxY + alturaCaixa > getHeight() - 10) {
                boxY = cy - alturaCaixa - 30;
            }

            // 3. Desenhar a linha tracejada a ligar o porto à caixa
            g.setColor(Color.GRAY);
            Stroke linhaOriginal = g.getStroke();
            g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0));

            // Calcula o ponto da caixa onde a linha vai colar (esquerda ou direita dependendo de onde a caixa está)
            int pontoAncoragemX = (boxX > cx) ? boxX : boxX + larguraCaixa;
            int pontoAncoragemY = (boxY > cy) ? boxY : boxY + alturaCaixa;
            g.drawLine(cx, cy, pontoAncoragemX, pontoAncoragemY);

            g.setStroke(linhaOriginal); // Repõe o traço normal do Java

            // Desenha Fundo Branco (ligeiramente transparente) e Borda Preta
            g.setColor(new Color(255, 255, 255, 200));
            g.fillRect(boxX, boxY, larguraCaixa, alturaCaixa);
            g.setColor(Color.BLACK);
            g.drawRect(boxX, boxY, larguraCaixa, alturaCaixa);

            // 5. Escrever as linhas de texto da fila de espera
            int textY = boxY + 15;
            for (String linha : linhasTexto) {
                g.drawString(linha, boxX + 5, textY);
                textY += 15;
            }
        }
    }

    private void desenharRelogio(Graphics2D g) {
        String textoTempo = String.format("Tempo: %.1f", snapshot.getTempoSimulacao());

        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.BLACK);

        FontMetrics metricas = g.getFontMetrics();
        int larguraTexto = metricas.stringWidth(textoTempo);

        int textoX = telaX(0) - larguraTexto - 45;

        int textoY = telaY(0) - 400;

        g.drawString(textoTempo, textoX, textoY);
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

    private void desenharCaixaCorrente(Graphics2D g) {
        if (corrente == null) return;

        int largura = 130;
        int altura = 110;
        int corte = 20; // O tamanho do canto cortado no topo direito

        // Posiciona a caixa à esquerda do Eixo Y
        int boxX = telaX(0) - largura - 30;
        int boxY = telaY(0) - altura - 200; // Acima da origem

        // Coordenadas dos 5 pontos do polígono
        int[] xPoints = {boxX, boxX + largura - corte, boxX + largura, boxX + largura, boxX};
        int[] yPoints = {boxY, boxY, boxY + corte, boxY + altura, boxY + altura};

        // Sombra
        int offsetSombra = 5;
        int[] sombraX = {boxX + offsetSombra, boxX + largura - corte + offsetSombra, boxX + largura + offsetSombra, boxX + largura + offsetSombra, boxX + offsetSombra};
        int[] sombraY = {boxY + offsetSombra, boxY + offsetSombra, boxY + corte + offsetSombra, boxY + altura + offsetSombra, boxY + altura + offsetSombra};

        g.setColor(new Color(130, 145, 165)); // Azul escuro para a sombra
        g.fillPolygon(sombraX, sombraY, 5);

        // Desenhar o fundo
        g.setColor(new Color(230, 245, 225));
        g.fillPolygon(xPoints, yPoints, 5);

        // Desenhar o texto
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 18));

        int textX = boxX + 15;
        g.drawString("Velocidade", textX, boxY + 25);
        g.drawString("da corrente", textX, boxY + 45);

        g.setFont(new Font("Times New Roman", Font.BOLD, 20));

        g.drawString("X = " + String.format("%.0f", corrente.getX()), textX, boxY + 80);
        g.drawString("Y = " + String.format("%.0f", corrente.getY()), textX, boxY + 100);
    }
}
