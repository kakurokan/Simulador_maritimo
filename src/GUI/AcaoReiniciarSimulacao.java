package GUI;

import Engine.Simulador;
import Engine.Tempestade;
import Engine.Vetor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AcaoReiniciarSimulacao extends AbstractAction {
    private final Simulador simulador;
    private final PainelMapa painel;
    private final Runnable rotinaDeTrafego;

    public AcaoReiniciarSimulacao(Simulador simulador, PainelMapa painel, Runnable rotinaDeTrafego) {
        super("Reiniciar");

        this.simulador = simulador;
        this.painel = painel;
        this.rotinaDeTrafego = rotinaDeTrafego;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Vetor corrente = DialogoCorrente.pedirCorrente(painel.getCorrente());

        if (corrente != null) {

            simulador.setCorrente(corrente);
            painel.setCorrente(corrente);

            List<Tempestade> novasTempestades = simulador.reiniciarSimulacao();
            painel.setTempestades(novasTempestades);
            rotinaDeTrafego.run();
            painel.atualizarSnapshot(simulador.gerarSnapshot());
            painel.repaint();
        }
    }
}
