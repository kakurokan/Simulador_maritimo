import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestorMaritimoTest {

    private GestorMaritimo gestor;
    private List<Navio> naviosSistema;
    private Navio navio;
    private Navio navio1;
    private Navio navio2;
    private List<Route> rotas;
    private List<Obstaculo> obstaculos;

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



        navio = new Navio(new Circulo(new Ponto(0,0), 5), 20, "A10");
        navio1 = new Navio(new Circulo(new Ponto(0,0), 5), 20, "A11");
        navio2 = new Navio(new Circulo(new Ponto(0,0), 5), 20, "A12");


        naviosSistema = new ArrayList<>(List.of(navio, navio1, navio2));


        gestor = new GestorMaritimo(rotas, obstaculos, naviosSistema);
    }

    @Test
    void atualizarRota() {

        Porto portodestino = new Porto("Lisboa City",new Ponto(3,5),naviosSistema,gestor);

        navio2.setDestino(portodestino);

        gestor.atualizarRota(navio2);

        Route melhorRotaEsperada = new Route(List.of(
                new Ponto(0,0), new Ponto(1,1), new Ponto(3,2), new Ponto(3,5)
        ));
        assertEquals(melhorRotaEsperada.getSegmentos(),navio2.getSegmentosRota());
    }

    @Test
    void atualizarPosicoes() {

        Ponto posicaoFinal = new Ponto(3,5);
        gestor.atualizarPosicoes(navio,posicaoFinal);

        assertEquals(posicaoFinal,navio.getPosicao());
    }

    @Test
    void libertarNavio() {

        Porto porto= new Porto("Albufeira City",new Ponto(0,0), naviosSistema,gestor);
        gestor.libertarNavio(porto,navio2);

        assertInstanceOf(NavioNavegando.class, navio2.getEstado());

    }
}