package Engine;

public class NavioAguardando implements EstadoNavio {
    @Override
    public void atualizar(Navio navio, double delta, Vetor velocidadeCorrente) {
        if (navio.isEmColisao()) {
            //Pede para torre para sair
            navio.getTorre().atualizarPosicoes(navio);
        } else {
            navio.mudarEstado(new NavioNavegando());
        }
    }
}
