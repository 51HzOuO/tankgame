package com.teng;

import java.awt.*;

public class IronPlate {
    private int x, y;
    private int width, height;

    public IronPlate(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    // Getter and Setter methods
    // ...
}
