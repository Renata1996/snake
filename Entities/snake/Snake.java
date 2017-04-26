package Entities.snake;

import java.awt.*;

/**
 * Created by Рената on 18.02.2017.
 */
public interface Snake {
    boolean move(int direction);

    void paint(Graphics g);

    boolean isInsideSnake(int x, int y);
}
