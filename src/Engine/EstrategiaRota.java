package Engine;

import java.util.List;

public interface EstrategiaRota {
    public Route caminhos(Ponto origem, Ponto destino, List<Navio> navios);
}
