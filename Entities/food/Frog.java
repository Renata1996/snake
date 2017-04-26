package Entities.food;

import Entities.snake.Snake;

import java.awt.*;

/**
 * Created by Рената on 18.02.2017.
 */
public interface Frog {
    void eat();

    int getX();

    int getY();

    boolean isEaten();

    void next(Snake snake);

    void paint(Graphics g);

}
