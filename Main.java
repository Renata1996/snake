import swing.GameBoard;

import javax.swing.*;

/**
 * Created by Рената on 18.02.2017.
 */
public class Main {
    private GameBoard form;

    public Main() {
        SwingUtilities.invokeLater(() -> {
            form = new GameBoard();
            form.setVisible(true);
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
