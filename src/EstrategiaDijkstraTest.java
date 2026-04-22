import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstrategiaDijkstraTest {

    /* Se tiver vontade separo os testes (#gemini hater)*/
    @Test
    void caminhos() {
        /*Primeiro teste com obstaculo no meio da melhor rota*/
        List<Ponto> pontosrota1 = new ArrayList<Ponto>();
        List<Ponto> pontosrota2 = new ArrayList<Ponto>();

        pontosrota1.add(new Ponto(0,0));
        pontosrota1.add(new Ponto(1,1));
        pontosrota1.add(new Ponto(3,2));
        pontosrota1.add(new Ponto(3,5));

        pontosrota2.add(new Ponto(9,1));
        pontosrota2.add(new Ponto(5,1));
        pontosrota2.add(new Ponto(3,2));
        pontosrota2.add(new Ponto(2,3));
        pontosrota2.add(new Ponto(0,4));
        pontosrota2.add(new Ponto(3,5));

        Route rota1 = new Route(pontosrota1);
        Route rota2 = new Route(pontosrota2);

        List<Route> rotas= new ArrayList<>();
        rotas.add(rota1);
        rotas.add(rota2);

        Ponto[] pontos = new Ponto[3];
        pontos[0] = new Ponto(2,4);
        pontos[1] = new Ponto(4,5);
        pontos[2] = new Ponto(5,4);
        List<ObstaculoEstatico> obstaculos = new ArrayList<>();

        obstaculos.add(new Triangulo(pontos));

        Grafo grafo = new Grafo(rotas,obstaculos);

        Ponto origem = new Ponto(0,0);
        Ponto destino = new Ponto(3,5);

        List<Ponto> pontosesperada = new ArrayList<>();
        pontosesperada.add(origem);
        pontosesperada.add(new Ponto(1,1));
        pontosesperada.add(new Ponto(3,2));
        pontosesperada.add(new Ponto(2,3));
        pontosesperada.add(new Ponto(0,4));
        pontosesperada.add(destino);

        Route routeesperada = new Route(pontosesperada);

        EstrategiaDijkstra dijkstra = new EstrategiaDijkstra(grafo);

        List<Tempestade> tempestades = new ArrayList<>();
        List<Navio> navios = new ArrayList<>();

        assertEquals(routeesperada, dijkstra.caminhos(origem,destino,tempestades,navios));



        /*Segundo teste sem obstaculo*/
        obstaculos.clear();
        grafo = new Grafo(rotas,obstaculos);
        pontosesperada = new ArrayList<>();
        pontosesperada.add(origem);
        pontosesperada.add(new Ponto(1,1));
        pontosesperada.add(new Ponto(3,2));
        pontosesperada.add(destino);

        routeesperada = new Route(pontosesperada);
        dijkstra = new EstrategiaDijkstra(grafo);

        assertEquals(routeesperada,dijkstra.caminhos(origem,destino,tempestades,navios));

    }
}