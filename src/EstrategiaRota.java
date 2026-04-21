import java.util.List;

public interface EstrategiaRota {
    public Route caminhos(Ponto origem, Ponto destino, List<Route> rotas, List<Tempestade> tempestades, List<Navio> navios);
}
