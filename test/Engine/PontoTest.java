package Engine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PontoTest {

    @Test
    void getX_PontoInstanciado_RetornaCoordenadaXCorreta() {
        Ponto p1 = new Ponto(2, 3);
        Ponto p2 = new Ponto(1.752, 0.2);

        assertEquals(2.0, p1.getX());
        assertEquals(1.752, p2.getX());
    }

    @Test
    void getY_PontoInstanciado_RetornaCoordenadaYCorreta() {
        Ponto p1 = new Ponto(3, 2);
        Ponto p2 = new Ponto(0.2, 1.752);

        assertEquals(2.0, p1.getY());
        assertEquals(1.752, p2.getY());
    }

    @Test
    void toString_PontoInstanciado_RetornaStringFormatadaComDuasCasasDecimais() {
        Ponto p1 = new Ponto(3, 4);
        Ponto p2 = new Ponto(-2.4, 7.512);

        assertEquals("(3.00,4.00)", p1.toString());
        assertEquals("(-2.40,7.51)", p2.toString());
    }

    @Test
    void equals_PontosComMesmasCoordenadas_RetornaTrue() {
        Ponto p1 = new Ponto(2, 3);
        Ponto p2 = new Ponto(2, 3);

        assertEquals(p1, p2, "Pontos com as mesmas coordenadas devem ser considerados iguais.");
    }

    @Test
    void equals_PontosComDiferencaAcimaDoEpsilon_RetornaFalse() {
        Ponto p1 = new Ponto(2, 3);
        Ponto p2 = new Ponto(2.0000001, 3);

        assertNotEquals(p1, p2, "Pontos com diferença superior a Ponto.eps não devem ser iguais.");
    }

    @Test
    void equals_ComparacaoComNull_RetornaFalse() {
        Ponto p1 = new Ponto(2, 3);

        assertNotEquals(null, p1, "A comparação de um Ponto com null deve retornar false.");
    }

    @Test
    void subtracao_DoisPontos_RetornaPontoComDiferencaDasCoordenadas() {
        Ponto p1 = new Ponto(5.5, 10);
        Ponto p2 = new Ponto(2, 3);

        Ponto resultado = p1.subtracao(p2);

        assertEquals(3.5, resultado.getX(), Ponto.eps);
        assertEquals(7.0, resultado.getY(), Ponto.eps);
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 2, 9, 4.5, 10.30776",
            "0, 10, -5.62, 20, 11.47102",
            "1, 2, 1, 2, 0.0"
    })
    void distanciaPara_DiferentesCoordenadas_CalculaCorretamente(double x1, double y1, double x2, double y2, double distanciaEsperada) {
        Ponto p1 = new Ponto(x1, y1);
        Ponto p2 = new Ponto(x2, y2);

        double resultado = p1.distanciaPara(p2);

        assertEquals(distanciaEsperada, resultado, 0.00001, "A distância calculada não corresponde à esperada.");
    }
}