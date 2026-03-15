/**
 * Representa um círculo no sistema de coordenadas cartesianas definido
 * por um ponto central e um raio.
 *
 * @author Léo Souza
 * @version 12/03/26
 * @inv o raio têm que ser maior que zero
 */
public class Circulo extends FiguraGeometrica {
    private final Ponto centro;
    private final double raio;

    /**
     * Constrói um objeto Circulo com um ponto central e um raio especificado.
     *
     * @param centro O ponto representando o centro do círculo. Não pode ser nulo.
     * @param raio   O raio do círculo. Deve ser um valor positivo.
     * @pre raio > 0 e centro != null
     * @pos getRaio() = raio
     * @pos getCentro() = uma cópia do objeto centro
     */
    public Circulo(Ponto centro, double raio) {
        if (raio < Ponto.eps) {
            IO.println("Circulo:iv");
            System.exit(0);
        }

        this.centro = new Ponto(centro.getX(), centro.getY());
        this.raio = raio;
    }

    /**
     * Retorna o ponto representando o centro do círculo no sistema de coordenadas cartesianas.
     * Este método fornece acesso ao ponto central armazenado internamente.
     *
     * @return o ponto que representa o centro do círculo.
     */
    public Ponto getCentro() {
        return centro;
    }

    /**
     * Retorna o valor do raio do círculo.
     * O raio é sempre um valor positivo que representa a distância do centro
     * do círculo à sua borda.
     *
     * @return o valor do raio do círculo.
     */
    public double getRaio() {
        return raio;
    }
}
