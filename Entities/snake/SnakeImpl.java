package Entities.snake;


import Entities.*;
import Entities.Point;
import Entities.food.Frog;

import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Рената on 18.02.2017.
 */
public class SnakeImpl implements Snake {

    private ArrayList<Entities.Point> snake;
    private int length;
    private int cellSize;
    private int sizeX;
    private int sizeY;
    private Frog frog;
    private final int UP = 40;
    private final int DOWN = 38;
    private final int RIGHT = 39;
    private final int LEFT = 37;

    public SnakeImpl(int length, int cellSize, int sizeX, int sizeY, Frog frog) {
        snake = new ArrayList<>();
        this.length = length;
        this.cellSize = cellSize;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.frog = frog;

        snake.add(new Entities.Point(1 + length, 9, cellSize / 2 - 2, Color.yellow, cellSize / 2));
        for (int i = 0; i < length - 1; i++)
            snake.add(new Entities.Point(1 + length - i, 9, cellSize / 3, Color.yellow, cellSize / 2));
        snake.add(new Entities.Point(2, 9, cellSize / 3 - 1, Color.yellow, cellSize / 2));
    }

    public boolean isInsideSnake(int x, int y) {
        for (Entities.Point point : snake) {
            if ((point.getX() == x) && (point.getY() == y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFood(Frog frog) {
        return ((snake.get(0).getX() == frog.getX()) && (snake.get(0).getY() == frog.getY()));
    }

    @Override
    public boolean move(int direction) {
        int x = snake.get(0).getX();
        int y = snake.get(0).getY();
        if (direction == LEFT) {
            x--;
        }
        if (direction == RIGHT) {
            x++;
        }
        if (direction == DOWN) {
            y--;
        }
        if (direction == UP) {
            y++;
        }
        if (isInsideSnake(x, y)) {
            return false;
        }
        if (x < 2 || x > sizeX * 2 / cellSize || y < 8 || y > sizeY * 2 / cellSize + 10) {
            return false;
        }

        snake.remove(0);
        snake.add(0, new Entities.Point(x, y, cellSize / 2 - 2, Color.yellow, cellSize / 2));
        snake.add(1, new Entities.Point(x, y, cellSize / 3, Color.yellow, cellSize / 2));
        snake.remove(snake.size() - 2);
        snake.add(snake.size() - 2, new Entities.Point(snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY(), cellSize / 3 - 1, Color.yellow, cellSize / 2));

        if (isFood(frog)) {
            frog.eat();
        } else {
            snake.remove(snake.size() - 1);
        }
        return true;
    }

    public void paint(Graphics g) {
        for (Entities.Point point : snake) {
            point.paint(g);
        }
    }

}
