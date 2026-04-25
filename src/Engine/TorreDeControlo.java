package Engine;

public interface TorreDeControlo {
    public void atualizarRota(Navio navio);

    public void atualizarPosicoes(Navio navio);

    public void libertarNavio(Porto origem, Navio navio);

    public void navioTerminouPercurso(Navio navio);
}
