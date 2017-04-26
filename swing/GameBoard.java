package swing;

import Entities.food.Frog;
import Entities.food.GreenFrogImpl;
import Entities.snake.Snake;
import Entities.snake.SnakeImpl;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoard extends JFrame {

    private JPanel contentPane;
    private JPanel board;
    private JTextArea textArea1;
    private JButton stopButton;
    private JButton pauseButton;
    private JButton startButton;
    private JTextArea textArea2;
    private int n;
    private int m;
    private Snake snake;
    private int speed;
    private volatile int direction;
    private Frog frog;
    private int goals;
    private boolean start;
    private final int cellSize = 20;

    {
        start = false;
        direction = 39;
        speed = 10;
        n = 400;
        m = 400;
    }

    public GameBoard() {
        $$$setupUI$$$();

        createUIComponents();
        setContentPane(contentPane);
        setSize(n + 100, m + 100);
        setResizable(false);
        board.setSize(n, m);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frog = new GreenFrogImpl(cellSize, n, m);
        snake = new SnakeImpl(5, cellSize, board.getWidth(), board.getHeight(), frog);
        intit();
    }

    private void intit() {
        initStartButton();
        initStopButton();
        initPauseButton();
    }

    private void initStopButton() {
        stopButton.addActionListener(e -> {
            if (start)
                stop();
        });
    }

    private void initStartButton() {
        startButton.addActionListener(e -> {
            if (!start) {
                direction = 39;
                start = true;
                snake = new SnakeImpl(5, 20, board.getWidth(), board.getHeight(), frog);
                goals = 0;
                intitSnake();
                intitFrog();
            }
        });
    }

    private void initPauseButton() {
        pauseButton.addActionListener(e -> {
            if (start) {
                stop();
                pauseButton.setText("Play");
            } else {
                start = true;
                intitSnake();
                intitFrog();
                pauseButton.setText("Pause");
            }

        });
    }

    private void intitFrog() {
        Thread thread = new Thread(() -> {
            while (start) {
                if (frog.isEaten()) {
                    frog.next(snake);
                    goals++;
                }
                repaint();
                try {
                    Thread.sleep(speed * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void intitSnake() {
        Thread thread = new Thread(() -> {
            while (start) {
                if (!snake.move(direction))
                    stop();
                repaint();
                try {
                    Thread.sleep(speed * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void stop() {
        start = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        snake.paint(g);
        frog.paint(g);
        textArea1.setText("  Score:" + goals);
    }


    private void createUIComponents() {
        textArea2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                direction = e.getKeyCode();
            }
        });
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setBackground(new Color(-16316665));
        contentPane.setMinimumSize(new Dimension(450, 500));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), 0, 0));
        panel1.setAlignmentY(0.0f);
        panel1.setAutoscrolls(true);
        panel1.setBackground(new Color(-16316665));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 4, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        stopButton = new JButton();
        stopButton.setBackground(new Color(-16316665));
        stopButton.setForeground(new Color(-1041));
        stopButton.setText("Stop");
        panel1.add(stopButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pauseButton = new JButton();
        pauseButton.setBackground(new Color(-16316665));
        pauseButton.setForeground(new Color(-1041));
        pauseButton.setText("Pause");
        panel1.add(pauseButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        startButton = new JButton();
        startButton.setBackground(new Color(-16316665));
        startButton.setForeground(new Color(-1041));
        startButton.setText("Start");
        panel1.add(startButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textArea1 = new JTextArea();
        textArea1.setBackground(new Color(-16316665));
        textArea1.setDisabledTextColor(new Color(-1041));
        textArea1.setEditable(false);
        textArea1.setForeground(new Color(-1041));
        panel1.add(textArea1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        board = new JPanel();
        board.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        board.setBackground(new Color(-16316665));
        contentPane.add(board, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        board.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-1041)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-16777216)));
        textArea2 = new JTextArea();
        textArea2.setBackground(new Color(-16316665));
        textArea2.setFont(new Font(textArea2.getFont().getName(), textArea2.getFont().getStyle(), 22));
        board.add(textArea2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
