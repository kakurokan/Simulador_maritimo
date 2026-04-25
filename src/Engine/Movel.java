package Engine;

public interface Movel {
    public boolean intersect(Circulo circulo);

    public void mover(double delta);

    public Ponto getPosicao();

    public abstract void atualizar(double delta);
}