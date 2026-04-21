public interface TorreDeControlo {
    public void atualizarRota(Navio navio);

    public void atualizarPosicoes(Navio navio, Ponto posicao);

    public void libertarNavio(Porto origem, Navio navio);
}
