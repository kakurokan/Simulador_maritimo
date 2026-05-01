package Engine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class VetorTest {

    @Test
    void construtor_VetorNulo_LancaIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Vetor(0, 0);
        });

        assertEquals("Engine.Vetor:iv", exception.getMessage());
    }

    @Test
    void construtor_ComponentesAbaixoDoEpsilon_LancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Vetor(1e-10, 1e-10);
        }, "Um vetor com componentes positivas muito próximas de zero deve ser rejeitado.");
    }

    @Test
    void construtor_ComponentesNegativasAbaixoDoEpsilon_LancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Vetor(-0.5e-9, -0.5e-9);
        }, "Um vetor com componentes negativas muito próximas de zero deve ser rejeitado.");
    }

    @Test
    void construtor_ComponentesValidas_NaoLancaExcecao() {
        assertDoesNotThrow(() -> new Vetor(1, 0));
        assertDoesNotThrow(() -> new Vetor(0, 1));
    }

    @ParameterizedTest
    @CsvSource({
            "2, 3, 2.0",
            "1.752, 0.2, 1.752"
    })
    void getX_VetorInstanciado_RetornaCoordenadaXCorreta(double x, double y, double xEsperado) {
        Vetor v = new Vetor(x, y);
        assertEquals(xEsperado, v.getX());
    }

    @ParameterizedTest
    @CsvSource({
            "3, 2, 2.0",
            "0.2, 1.752, 1.752"
    })
    void getY_VetorInstanciado_RetornaCoordenadaYCorreta(double x, double y, double yEsperado) {
        Vetor v = new Vetor(x, y);
        assertEquals(yEsperado, v.getY());
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, '[3.00,4.00]'",
            "-2.4, 7.512, '[-2.40,7.51]'"
    })
    void toString_VetorInstanciado_RetornaStringFormatadaComDuasCasasDecimais(double x, double y, String stringEsperada) {
        Vetor v = new Vetor(x, y);
        assertEquals(stringEsperada, v.toString());
    }

    @Test
    void equals_VetoresComMesmasCoordenadas_RetornaTrue() {
        Vetor v1 = new Vetor(1.2, -1.2);
        Vetor v2 = new Vetor(1.2, -1.2);

        assertEquals(v1, v2, "Vetores com as mesmas componentes devem ser considerados iguais.");
    }

    @Test
    void equals_VetoresComDiferencaAcimaDoEpsilon_RetornaFalse() {
        Vetor v1 = new Vetor(-1.2, 0.35);
        Vetor v2 = new Vetor(-1.2, 0.365);

        assertNotEquals(v1, v2, "Vetores com diferença superior ao eps não podem ser iguais.");
    }

    @Test
    void produtoVetorial_DoisVetores_RetornaProdutoCorreto() {
        Vetor v1 = new Vetor(5.6, 7.8);
        Vetor v2 = new Vetor(9.2, 8.2);

        assertEquals(-25.84, v1.produtoVetorial(v2), Ponto.eps);
    }

    @Test
    void modulo_VetorValido_RetornaModuloCorreto() {
        Vetor v = new Vetor(3.74, -2.45);

        assertEquals(4.471028964, v.modulo(), Ponto.eps);
    }

    @Test
    void produtoInterno_DoisVetores_RetornaProdutoEscalarCorreto() {
        Vetor v1 = new Vetor(2, 1);
        Vetor v2 = new Vetor(6.2, 3.5);

        assertEquals(15.90, v1.produtoInterno(v2), Ponto.eps);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 3, 4, 5.5, 0.953230637699",
            "-7, 4, 16, 1, -0.835604010078"
    })
    void cossineSimilarity_DoisVetores_CalculaSimilaridadeCorreta(double x1, double y1, double x2, double y2, double similaridadeEsperada) {
        Vetor v1 = new Vetor(x1, y1);
        Vetor v2 = new Vetor(x2, y2);

        assertEquals(similaridadeEsperada, v1.cossineSimilarity(v2), Ponto.eps);
    }

    @Test
    void mult_VetorPorEscalar_RetornaVetorMultiplicado() {
        Vetor original = new Vetor(2.34, 5.42);
        Vetor esperado = new Vetor(11.7, 27.1);

        Vetor resultado = original.multi(5);

        assertEquals(esperado.getX(), resultado.getX(), Ponto.eps);
        assertEquals(esperado.getY(), resultado.getY(), Ponto.eps);
    }

    @Test
    void sub_DoisVetores_RetornaVetorSubtraido() {
        Vetor v1 = new Vetor(2, -3);
        Vetor v2 = new Vetor(4, 6);
        Vetor esperado = new Vetor(-2, -9);

        Vetor resultado = v1.sub(v2);

        assertEquals(esperado.getX(), resultado.getX(), Ponto.eps);
        assertEquals(esperado.getY(), resultado.getY(), Ponto.eps);
    }

    @Test
    void intersect_VetorCruzaSegmento_RetornaPontoDeIntersecao() {
        Vetor v = new Vetor(2, 2);
        SegmentoReta sr = new SegmentoReta(new Ponto(0, 1), new Ponto(4, 1));
        Ponto esperado = new Ponto(1, 1);

        assertEquals(esperado, v.intersect(sr));
    }

    @Test
    void intersect_VetorNaoCruzaSegmento_RetornaNull() {
        Vetor v = new Vetor(1, 4);
        SegmentoReta sr = new SegmentoReta(new Ponto(4, 4), new Ponto(6, 6));

        assertNull(v.intersect(sr), "Um vetor que não cruza o segmento deve retornar null.");
    }
}