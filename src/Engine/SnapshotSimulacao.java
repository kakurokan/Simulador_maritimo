package Engine;

import java.util.List;
import java.util.Map;

public class SnapshotSimulacao {

    private final Map<String, List<NavioEmEspera>> naviosEmEsperaPorPorto;
    private final List<Ponto> posicoesNavios;

    public SnapshotSimulacao(Map<String, List<NavioEmEspera>> naviosEmEsperaPorPorto, List<Ponto> posicoesNavios) {
        this.naviosEmEsperaPorPorto = Map.copyOf(naviosEmEsperaPorPorto);
        this.posicoesNavios = List.copyOf(posicoesNavios);
    }

    public Map<String, List<NavioEmEspera>> getNaviosEmEsperaPorPorto() {
        return naviosEmEsperaPorPorto;
    }

    public List<Ponto> getPosicoesNavios() {
        return posicoesNavios;
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
}
