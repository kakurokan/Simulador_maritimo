import GUI.JanelaPrincipal;

import javax.swing.*;

public class Cliente {
    static void main() {
        

        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal gui = new JanelaPrincipal(simulador);
            gui.iniciar();
        });
    }
}
