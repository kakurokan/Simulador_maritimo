package Engine;

public interface Movel {
    public boolean intersect(Movel objeto);

    public void mover(double delta, Vetor velocidadeCorrente);

    public Ponto getPosicao();

    public void atualizar(double delta, Vetor velocidadeCorrente);

    public Circulo getArea();

    public Vetor getDirecao(Vetor velocidadeCorrente);
}