package Entities.food;

import Entities.Point;
import Entities.snake.Snake;

import java.awt.*;
import java.util.Random;

/**
 * Created by Рената on 20.02.2017.
 */
public class GreenFrogImpl implements Frog {

    private Point point;
    private int cellSize;
    private int sizeX;
    private int sizeY;

    public GreenFrogImpl(int cellSize, int sizeX, int sizeY) {
        this.sizeY = sizeY;
        this.sizeX = sizeX;
        this.cellSize = cellSize;
        point = new Point(15, 15, cellSize / 3, Color.GREEN,cellSize/2);
    }

    public void eat() {
        point.setXY(-1, -1);
    }

    @Override
    public int getX() {
        return point.getX();
    }

    @Override
    public int getY() {
        return point.getY();
    }

    @Override
    public boolean isEaten() {
        return point.getX() == -1;
    }

    public void paint(Graphics g) {
        point.paint(g);
    }

    public void next(Snake snake) {
        int x,y;
        do{
            x = new Random().nextInt(sizeX/cellSize);
            y = new Random().nextInt(sizeY/cellSize);
            System.out.println(x+" "+y);
        }
        while (x<5 || y<8); // отступы у формы
        point.setXY(x, y);
    }

}
