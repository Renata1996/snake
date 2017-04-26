package Entities;

import java.awt.*;

/**
 * Created by Рената on 18.02.2017.
 */
public class Point {

    private Color color;
    private int radius;
    private int x;
    private int y;
    private int cellSize;

    public Point(int x, int y, int radius, Color color,int cellSize) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.radius = radius;
        this.cellSize = cellSize;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x*cellSize , y*cellSize , radius, radius);
    }

    public Color getColor() {
        return color;
    }

    public int getRadius() {
        return radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
