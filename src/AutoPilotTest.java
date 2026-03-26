import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoPilotTest {

    @Test
    void speed() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        Vetor windSpeed = new Vetor(0.2, 0.2);
        double linearSpeed = 0.4;
        double time = ap.time(linearSpeed);
        Vetor result = ap.speed(windSpeed, time);
        assertEquals(-0.2, result.getX(), Ponto.eps);
        assertEquals(0.2, result.getY(), Ponto.eps);

        ap = new AutoPilot(new Ponto(3, 4), new Ponto(3, 2));
        result = ap.speed(windSpeed, time);
        assertEquals(-0.2, result.getX(), Ponto.eps);
        assertEquals(-0.6, result.getY(), Ponto.eps);
    }

    @Test
    void time() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        double speed = 0.4;
        double result = ap.time(speed);
        assertEquals(5, result, 0.01);
    }
}