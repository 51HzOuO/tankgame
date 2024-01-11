package com.teng;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import java.awt.AlphaComposite;

public class Panel_ extends JPanel implements KeyListener {

    MyTank myTank = new MyTank(100, 100, 0);

    public Panel_() {
        this.setBackground(Color.GRAY);

    }

    int x = 0;
    int y = 0;
    int direct = 0;
    private static final int RATE = 5;

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);

    }

    // public void moveTank(String key) {
    // char c = Character.toLowerCase(key.charAt(0));
    // switch (c) {
    // case 'w':
    // myTank.setDirect(0);
    // myTank.setY(myTank.getY() - 5);
    // break;
    // case 'd':
    // myTank.setDirect(1);
    // myTank.setX(myTank.getX() + 5);
    // break;
    // case 's':
    // myTank.setDirect(2);
    // myTank.setY(myTank.getY() + 5);
    // break;
    // case 'a':
    // myTank.setDirect(3);
    // myTank.setX(myTank.getX() - 5);
    // break;
    // }

    // }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Graphics2D g2d = (Graphics2D) g.create();
        // g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        // g2d.setColor(setColor());
        // g2d.fillRect(0, 0, 20, 100);
        // g2d.fillRect(50, 0, 20, 100);

        // g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        // g2d.fillRect(20, 20, 30, 60);

        // g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        // g2d.fillOval(20, 50 - 15, 30, 30);
        // g2d.fillRect(35 - 2, 0, 4, 50);

        // g2d.dispose(); // Dispose of this graphics context and release any system
        // resources that it is
        // // using

        Image image = Toolkit.getDefaultToolkit().getImage("pic/bj.jpg");

        // Image image2 =
        // Toolkit.getDefaultToolkit().getImage(Panel_.class.getResource("bj.jpg"));

        g.drawImage(image, 100, 100, 108, 108, this);

        // moveTank();
        drawTank(100 + x * RATE, 100 + y * RATE, g, direct, 0, Color_.RED);
        Color_[] values = Color_.values();
        Random random = new Random();
        drawTank(200, 0, g, 1, 1, values[random.nextInt(values.length)]);
        drawTank(300, 0, g, 1, 1, values[random.nextInt(values.length)]);
        drawTank(400, 0, g, 1, 1, values[random.nextInt(values.length)]);

    }

    public static void drawTank(int x, int y, Graphics g, int direct, int type, Color_ color) {

        switch (color) {
            case RED:
                g.setColor(Color.RED);
                break;
            case GREEN:
                g.setColor(Color.GREEN);
                break;
            case BLUE:
                g.setColor(Color.BLUE);
                break;
            case CYAN:
                g.setColor(Color.CYAN);
                break;
            case MAGENTA:
                g.setColor(Color.MAGENTA);
                break;
            case YELLOW:
                g.setColor(Color.YELLOW);
                break;
            case BLACK:
                g.setColor(Color.BLACK);
                break;
            case WHITE:
                g.setColor(Color.WHITE);
                break;
        }
        // switch (type) {

        // case 0:
        // g.setColor(Color.CYAN);
        // break;
        // case 1:
        // g.setColor(Color.RED);
        // break;
        // }

        switch (direct) {
            case 0: // 上
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g2d.fill3DRect(x, y, 20, 100, false);
                g2d.fill3DRect(x + 50, y, 20, 100, false);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.fill3DRect(x + 20, y + 20, 30, 60, false);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
                g2d.fillOval(x + 20, y + 35, 30, 30);
                g2d.fill3DRect(x + 35, y, 4, 50, false);

                g2d.dispose();
                break;

            case 1: // 右
                g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g2d.fillRect(x, y, 100, 20);
                g2d.fillRect(x, y + 50, 100, 20);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.fillRect(x + 20, y + 20, 60, 30);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
                g2d.fillOval(x + 35, y + 20, 30, 30);
                g2d.fillRect(x + 50, y + 35, 50, 4);

                g2d.dispose();
                break;

            case 2: // 下
                g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g2d.fillRect(x, y, 20, 100);
                g2d.fillRect(x + 50, y, 20, 100);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.fillRect(x + 20, y + 20, 30, 60);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
                g2d.fillOval(x + 20, y + 35, 30, 30);
                g2d.fillRect(x + 35, y + 50, 4, 50);

                g2d.dispose();
                break;

            case 3: // 左
                g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g2d.fillRect(x, y, 100, 20);
                g2d.fillRect(x, y + 50, 100, 20);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.fillRect(x + 20, y + 20, 60, 30);

                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
                g2d.fillOval(x + 35, y + 20, 30, 30);
                g2d.fillRect(x, y + 35, 50, 4);

                g2d.dispose();
                break;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // System.out.println("keyPressed");

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                y -= 1;
                direct = 0;
                break;
            case KeyEvent.VK_S:
                y += 1;
                direct = 2;
                break;
            case KeyEvent.VK_A:
                x -= 1;
                direct = 3;
                break;
            case KeyEvent.VK_D:
                x += 1;
                direct = 1;

                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
