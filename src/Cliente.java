import Engine.*;
import GUI.JanelaPrincipal;

import javax.swing.*;
import java.util.*;

public class Cliente {
    public static void main(String[] args) {

        // 1. PONTOS GEOMÉTRICOS (Valores curtos para caberem bem no ecrã com a Escala de 50x)
        Ponto pA = new Ponto(2, 2);
        Ponto pB = new Ponto(12, 4);
        Ponto pC = new Ponto(8, 10);
        Ponto pD = new Ponto(16, 12);

        // 2. ROTAS
        List<Route> rotas = Arrays.asList(
                new Route(Arrays.asList(pA, pB)),
                new Route(Arrays.asList(pB, pC)),
                new Route(Arrays.asList(pC, pA)),
                new Route(Arrays.asList(pB, pD))
        );

        // 3. POSIÇÕES DOS PORTOS (Para as caixas flutuantes da GUI)
        Map<String, Ponto> posicoesPortos = new HashMap<>();
        posicoesPortos.put("Porto de Lisboa", pA);
        posicoesPortos.put("Porto de Faro", pB);
        posicoesPortos.put("Porto de Vigo", pC);
        posicoesPortos.put("Porto de Sagres", pD);

        // 4. OBSTÁCULOS ESTÁTICOS (Ex: Uma Ilha no meio do mar)
        Triangulo ilhaBege = new Triangulo(new Ponto[]{
                new Ponto(5, 5), new Ponto(7, 8), new Ponto(9, 5)
        });
        List<Poligono> obstaculosEstaticos = List.of(ilhaBege);

        // 5. TEMPESTADES (Zonas azuis)
        Tempestade tempestadeSul = new Tempestade(new Circulo(new Ponto(10, 2), 1.5));
        List<Tempestade> tempestades = List.of(tempestadeSul);

        // 6. MOTOR DO JOGO (Juntar os obstáculos todos para a física funcionar)
        List<Obstaculo> todosObstaculos = new ArrayList<>();
        todosObstaculos.addAll(obstaculosEstaticos);
        todosObstaculos.addAll(tempestades);

        // Inicialização da Torre e Portos
        TorreDeControlo torre = new GestorMaritimo(rotas, todosObstaculos);

        Porto porto1 = new Porto("Porto de Lisboa", pA, torre);
        Porto porto2 = new Porto("Porto de Faro", pB, torre);
        Porto porto3 = new Porto("Porto de Vigo", pC, torre);
        Porto porto4 = new Porto("Porto de Sagres", pD, torre);

        List<Porto> portos = Arrays.asList(porto1, porto2, porto3, porto4);

        Vetor corrente = new Vetor(1, 0);

        Simulador simulador = new Simulador(
                corrente,
                rotas,
                portos,
                todosObstaculos,
                torre
        );

        // 7. CRIAR TRÁFEGO (Adicionar Navios)
        // (Assumindo que a assinatura é adicionarNavio(velocidade, tempoPartida, portoDestino))
        // Ajuste estes parâmetros caso o seu método adicionarNavio seja ligeiramente diferente
        porto1.adicionarNavio(5, 0, porto2); // Navio arranca no tempo 0 para Faro
        porto1.adicionarNavio(5, 5, porto3); // Navio arranca no tempo 5 para Vigo
        porto2.adicionarNavio(5, 2, porto1); // Navio arranca no tempo 2 para Lisboa
        porto3.adicionarNavio(5, 0, porto4); // Navio arranca no tempo 0 para Sagres

        // 8. ARRANCAR A INTERFACE GRÁFICA
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal gui = new JanelaPrincipal(
                    simulador,
                    rotas,
                    obstaculosEstaticos,
                    tempestades,
                    posicoesPortos,
                    corrente
            );
            gui.iniciar();
        });
    }
}