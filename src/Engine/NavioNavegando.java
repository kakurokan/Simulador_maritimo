package Engine;

public class NavioNavegando implements EstadoNavio {
    @Override
    public void atualizar(Navio navio, double delta, Vetor velocidadeCorrente) {
        navio.mover(delta, velocidadeCorrente);
    }
}
