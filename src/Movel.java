public interface Movel {
    public boolean intersect(Circulo circulo);

    public void mover();

    public Ponto getPosicao();

    public abstract void atualizar(double delta);
}