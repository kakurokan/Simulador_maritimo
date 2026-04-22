public class Tempestade extends ObstaculoMovel {
    private double tempoVida;

    public Tempestade(Circulo area, double velocidadeLinear, Vetor direcao, double tempoVida) {
        this.tempoVida = tempoVida;
        super(area, velocidadeLinear, direcao);
    }

    @Override
    public void atualizar(double delta) {
        
    }
}
