public abstract class ObstaculoMovel implements Obstaculo {
    protected double velocidadeLinear;
    protected Vetor direcao;
    protected Circulo area;

    public ObstaculoMovel(Circulo area, double velocidadeLinear, Vetor direcao) {

    }

    public boolean intersect(Circulo circulo) {
        return this.area.intersect(circulo);
    }

    public void mover() {

    }

    public Ponto getPosicao() {
        return null;
    }

    public abstract void atualizar(double delta);
}
