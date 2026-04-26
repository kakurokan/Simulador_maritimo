package Engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorTest {

    private GestorMaritimo gestor;
    private List<Movel> naviosSistema;
    private Navio navio;
    private List<Route> rotas;
    private List<Obstaculo> obstaculos;
    private Porto origem,destino;
    private List<Porto> portos;
    private Simulador simulador;
    private Vetor corrente;

    @BeforeEach
    void setUp() {
        Route rota1 = new Route(List.of(
                new Ponto(0,0), new Ponto(1,1), new Ponto(3,2), new Ponto(3,5)
        ));

        Route rota2 = new Route(List.of(
                new Ponto(9,1), new Ponto(5,1), new Ponto(3,2),
                new Ponto(2,3), new Ponto(0,4), new Ponto(3,5)
        ));

        rotas = new ArrayList<>(List.of(rota1, rota2));
        obstaculos = new ArrayList<>();
        gestor = new GestorMaritimo(rotas,obstaculos);
        origem = new Porto("Albufeira", new Ponto(0,0),gestor);
        destino = new Porto("Lisboa", new Ponto(50,40), gestor);
        portos = List.of(origem,destino);
        navio = origem.adicionarNavio(5,2,destino);
        naviosSistema = List.of(navio);
        corrente = new Vetor(-3,2);
        simulador = new Simulador(corrente,rotas,portos,naviosSistema,obstaculos);
    }

    @Test
    void atualizar() {
        navio.mudarEstado(new NavioNavegando());

        double x_inicial = navio.getPosicao().getX();
        double y_inicial = navio.getPosicao().getY();

        simulador.atualizar(1.0);

        Ponto novaPosicao = navio.getPosicao();

        assertNotNull(novaPosicao);
        boolean moveu = (novaPosicao.getX() != x_inicial) || (novaPosicao.getY() != y_inicial);
        assertTrue(moveu);
    }

    @Test
    void criarTempestade() {
        simulador.criarTempestade();
        assertFalse(simulador.getObstaculo().isEmpty());
    }

    @Test
    void getObstaculos(){
        obstaculos.add(
                new Tempestade(new Circulo(new Ponto(1,1),5))
        );
        Simulador simulador1 = new Simulador(corrente,rotas,portos,naviosSistema,obstaculos);
        assertEquals(obstaculos,simulador1.getObstaculo());
    }
}