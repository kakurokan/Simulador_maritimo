package Engine;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe {@code Engine.Navegante} é responsável por realizar cálculos relacionados à navegação
 * numa rota composta por segmentos de reta. Os cálculos incluem a determinação da
 * velocidade em cada segmento considerando a interferência de um vetor de vento, o tempo
 * total necessário para percorrer a rota e a posição em função de uma velocidade constante
 * e o tempo transcorrido.
 *
 * @author Léo Souza
 * @version 01/04/26
 */
public class Navegante {
    private List<SegmentoReta> segmentos;

    public Navegante() {
    }

    /**
     * Constrói uma instância de Engine.Navegante utilizando a rota fornecida.
     *
     * @param rota a rota contendo os segmentos para inicializar esta instância de Engine.Navegante
     * @pre rota != null e rota.getSegmentos() != null
     */
    public Navegante(Route rota) {
        mudarRota(rota);
    }

    /**
     * Altera a rota associada à instância do objeto, substituindo os segmentos
     * existentes pelos segmentos da nova rota fornecida.
     *
     * @param rota A nova rota contendo os segmentos a serem associados à instância.
     *             Deve ser um objeto {@code Engine.Route} válido cuja lista de segmentos
     *             não seja nula.
     * @pre rota != null e rota.getSegmentos() != null
     */
    public void mudarRota(Route rota) {
        this.segmentos = rota.getSegmentos();
    }

    /**
     * Calcula a velocidade por segmento de uma rota, considerando a velocidade do vento
     * e uma velocidade linear constante. Para cada segmento da rota, é calculado o tempo
     * requerido para atravessá-lo e, com base nesses parâmetros, determina-se a velocidade
     * resultante para o segmento.
     *
     * @param windSpeed   Um objeto {@code Engine.Vetor} representando a velocidade do vento.
     *                    Não pode ser {@code null}.
     * @param linearSpeed A velocidade linear constante utilizada para o cálculo.
     *                    Deve ser um valor positivo maior que zero.
     * @return Uma lista de objetos {@code Engine.Vetor} representando a velocidade em cada
     * segmento da rota, considerando os efeitos da velocidade do vento.
     * A lista estará vazia se não houver segmentos na rota.
     * @pre linearSpeed > 0
     */
    public List<Vetor> velocidadePorSegmento(Vetor windSpeed, double linearSpeed) {
        List<Vetor> velocidades = new ArrayList<>();
        for (SegmentoReta seg : segmentos) {
            AutoPilot ap = new AutoPilot(seg.getA(), seg.getB());
            double time = ap.time(linearSpeed);
            velocidades.add(ap.speed(windSpeed, time));
        }
        return velocidades;
    }

    /**
     * Calcula o tempo total necessário para percorrer a rota representada pelos segmentos,
     * considerando uma velocidade linear constante.
     * <p>
     * O método utiliza a sequência de segmentos da rota e, para cada um deles, calcula o tempo
     * requerido para atravessá-lo com base na velocidade linear fornecida. O tempo total é a soma
     * dos tempos necessários para percorrer todos os segmentos consecutivos.
     *
     * @param linearSpeed A velocidade linear constante utilizada para calcular o tempo de percurso.
     *                    Deve ser um valor positivo maior que zero.
     * @return O tempo total necessário para percorrer a rota em segundos. Se a lista de segmentos
     * estiver vazia, o retorno será 0.
     */
    public double tempoParaPercorrer(double linearSpeed) {
        double resultado = 0;
        for (SegmentoReta seg : segmentos) {
            AutoPilot ap = new AutoPilot(seg.getA(), seg.getB());
            resultado += ap.time(linearSpeed);
        }
        return resultado;
    }

    /**
     * Calcula a posição em uma sequência de segmentos a partir de uma velocidade linear
     * constante e um tempo transcorrido.
     * <p>
     * A posição é determinada percorrendo os segmentos sequencialmente, calculando a
     * distância a percorrer em cada segmento até que o tempo informado seja atingido.
     * Se o tempo transcorrido resultar numa posição além do último segmento,
     * a posição final será o ponto final do último segmento.
     *
     * @param velocidadeLinear A velocidade linear constante em unidades de comprimento por unidade
     *                         de tempo. Deve ser um valor positivo maior que zero.
     * @param tempo            O tempo transcorrido pelo movimento ao longo dos segmentos.
     *                         Deve ser um valor positivo maior ou igual a zero.
     * @return A posição representada por um ponto 2D no sistema de coordenadas cartesianas
     * correspondente ao local alcançado após o movimento. Se os segmentos estiverem
     * vazios, retorna null.
     * @pre tempo > 0 e velocidadeLinear > 0
     */
    public Ponto posicao(double velocidadeLinear, double tempo) {
        double percorrer = velocidadeLinear * tempo;

        for (SegmentoReta seg : segmentos) {
            Vetor r = new Vetor(seg.getA(), seg.getB());

            double distancia = r.modulo();

            if (percorrer <= distancia) {
                double percorreNoSegmento = percorrer / distancia;
                Vetor deslocamento = r.mult(percorreNoSegmento);
                Ponto inicio = seg.getA();

                return new Ponto(inicio.getX() + deslocamento.getX(),
                        inicio.getY() + deslocamento.getY());
            }

            percorrer -= distancia;
        }

        return segmentos.isEmpty() ? null : segmentos.getLast().getB();
    }

    public List<SegmentoReta> getSegmentos() {
        return segmentos;
    }
}
