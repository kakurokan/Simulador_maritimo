package Engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoPilotTest {
    
    @Test
    void speed_MovimentoSentidoPositivoComVento_CalculaVelocidadeAjustada() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        Vetor windSpeed = new Vetor(0.2, 0.2);
        double linearSpeed = 0.4;

        double time = ap.time(linearSpeed);
        Vetor result = ap.speed(windSpeed, time);

        assertEquals(-0.2, result.getX(), Ponto.eps, "A componente X da velocidade ajustada está incorreta.");
        assertEquals(0.2, result.getY(), Ponto.eps, "A componente Y da velocidade ajustada está incorreta.");
    }

    @Test
    void speed_MovimentoSentidoNegativoComVento_CalculaVelocidadeAjustada() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 4), new Ponto(3, 2));
        Vetor windSpeed = new Vetor(0.2, 0.2);
        double linearSpeed = 0.4;

        double time = ap.time(linearSpeed);
        Vetor result = ap.speed(windSpeed, time);

        assertEquals(-0.2, result.getX(), Ponto.eps, "A componente X da velocidade ajustada está incorreta.");
        assertEquals(-0.6, result.getY(), Ponto.eps, "A componente Y da velocidade ajustada está incorreta.");
    }

    @Test
    void time_VelocidadeLinearValida_CalculaTempoNecessarioCorretamente() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        double speed = 0.4;

        double result = ap.time(speed);

        assertEquals(5.0, result, 0.01, "O tempo para percorrer o trajeto deveria ser calculado dividindo a distância pela velocidade linear.");
    }
}