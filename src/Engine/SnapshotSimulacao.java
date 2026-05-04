package Engine;

import java.util.List;
import java.util.Map;

public class SnapshotSimulacao {

    private final Map<String, List<NavioEmEspera>> naviosEmEsperaPorPorto;
    private final List<DadosNavio> dadosNavios;

    public SnapshotSimulacao(Map<String, List<NavioEmEspera>> naviosEmEsperaPorPorto, List<DadosNavio> dadosNavios) {
        this.naviosEmEsperaPorPorto = Map.copyOf(naviosEmEsperaPorPorto);
        this.dadosNavios = dadosNavios;
    }

    public Map<String, List<NavioEmEspera>> getNaviosEmEsperaPorPorto() {
        return naviosEmEsperaPorPorto;
    }

    public List<DadosNavio> getDadosNavios() {
        return dadosNavios;
    }

    public Ponto[] getPosicoesNavios() {
        return null;
    }

    public static class NavioEmEspera {
        private final int horarioSaida;
        private final String destino;
        private final double velocidadeLinear;

        public NavioEmEspera(int horarioSaida, String destino, double velocidadeLinear) {
            this.horarioSaida = horarioSaida;
            this.destino = destino;
            this.velocidadeLinear = velocidadeLinear;
        }

        public int getHorarioSaida() {
            return horarioSaida;
        }

        public String getDestino() {
            return destino;
        }

        public double getVelocidadeLinear() {
            return velocidadeLinear;
        }

        @Override
        public String toString() {
            return String.format("T=%d, %s, %.2f", horarioSaida, destino, velocidadeLinear);
        }
    }

    public static class DadosNavio {
        private final Ponto posicao;
        private final Vetor direcao;
        private final boolean emColisao;
        private final double raioArea;

        public DadosNavio(Ponto posicao, Vetor direcao, boolean emColisao, double raioArea) {
            this.posicao = posicao;
            this.direcao = direcao;
            this.emColisao = emColisao;
            this.raioArea = raioArea;
        }

        public Ponto getPosicao() {
            return posicao;
        }

        public Vetor getDirecao() {
            return direcao;
        }

        public boolean isEmColisao() {
            return emColisao;
        }

        public double getRaioArea() {
            return raioArea;
        }
    }
}
