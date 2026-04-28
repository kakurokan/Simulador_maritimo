package Engine;

public interface Movel {
    public boolean intersect(Movel objeto);

    public void mover(double delta);

    public Ponto getPosicao();

    public void atualizar(double delta);

    public Circulo getArea();

    public Vetor getDirecao();
}