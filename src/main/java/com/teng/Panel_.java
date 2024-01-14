package com.teng;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;
import java.awt.AlphaComposite;

public class Panel_ extends JPanel implements KeyListener, Runnable {
    Random random = new Random();
    MyTank myTank;

    List<EnemyTank> enemyTanks = new Vector<>();
    List<Fire> bullets = new ArrayList<>();

    public void addBullet(Fire bullet) {
        bullets.add(bullet);

    }

    int enemyTankCount = 3;

    public Panel_() {
        this.setBackground(Color.GRAY);

        myTank = new MyTank(100, 100, 0, this);
        for (int i = 0; i < enemyTankCount; i++) {
            enemyTanks.add(new EnemyTank(random.nextInt(1100), random.nextInt(700), random.nextInt(4), this));
        }

    }

    // private int x = 0;
    // private int y = 0;
    // private int direct = 0;
    private int RATE = 4;

    @Override
    public void paint(Graphics g) {
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

        Color_[] values = Color_.values();
        g.setFont(new java.awt.Font("宋体", 1, 30));
        g.drawString("wasd移动  1,2,3,4变速", 800, 50);
        g.drawString("j发射子弹", 800, 100);
        g.setColor(getColor(values[random.nextInt(values.length)]));
        g.drawString("···", 10, 50);
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
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0, Color_.RED);

        // drawTank(enemyTanks.get(0).getX(), enemyTanks.get(0).getY(), g,
        // enemyTanks.get(0).getDirect(), 0, Color_.BLACK);
        // drawTank(enemyTanks.get(1).getX(), enemyTanks.get(1).getY(), g,
        // enemyTanks.get(1).getDirect(), 0, Color_.BLACK);
        // drawTank(enemyTanks.get(2).getX(), enemyTanks.get(2).getY(), g,
        // enemyTanks.get(2).getDirect(), 0, Color_.BLACK);
        for (EnemyTank enemyTank : enemyTanks) {
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0, Color_.BLACK);
        }
        for (Fire bullet : bullets) {
            bullet.draw(g);
        }
        // drawTank(200, 0, g, 1, 1, values[random.nextInt(values.length)]);
        // drawTank(300, 0, g, 1, 1, values[random.nextInt(values.length)]);
        // drawTank(400, 0, g, 1, 1, values[random.nextInt(values.length)]);
        // repaint();
    }

    private static Color getColor(Color_ color) {
        switch (color) {
            case RED:
                return Color.RED;
            case GREEN:
                return Color.GREEN;
            case BLUE:
                return Color.BLUE;
            case CYAN:
                return Color.CYAN;
            case MAGENTA:
                return Color.MAGENTA;
            case YELLOW:
                return Color.YELLOW;
            case BLACK:
                return Color.BLACK;
            case WHITE:
                return Color.WHITE;
            default:
                return Color.WHITE;
        }
    }

    public Fire fire = null;

    // private List<Fire> bullets = new ArrayList<>();

    private void drawTank(int x, int y, Graphics g, int direct, int type, Color_ color) {

        g.setColor(getColor(color));
        // switch (color) {
        // case RED:
        // g.setColor(Color.RED);
        // break;
        // case GREEN:
        // g.setColor(Color.GREEN);
        // break;
        // case BLUE:
        // g.setColor(Color.BLUE);
        // break;
        // case CYAN:
        // g.setColor(Color.CYAN);
        // break;
        // case MAGENTA:
        // g.setColor(Color.MAGENTA);
        // break;
        // case YELLOW:
        // g.setColor(Color.YELLOW);
        // break;
        // case BLACK:
        // g.setColor(Color.BLACK);
        // break;
        // case WHITE:
        // g.setColor(Color.WHITE);
        // break;
        // }
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
                myTank.moveUp(RATE);
                myTank.setDirect(0);
                // repaint();
                break;
            case KeyEvent.VK_S:
                myTank.moveDown(RATE);
                myTank.setDirect(2);
                // repaint();
                break;
            case KeyEvent.VK_A:
                myTank.moveLeft(RATE);
                myTank.setDirect(3);
                // repaint();
                break;
            case KeyEvent.VK_D:
                myTank.moveRight(RATE);
                myTank.setDirect(1);
                // repaint();
                break;
            case KeyEvent.VK_J:
                myTank.fire();
                // repaint();
                break;
            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_1:
                RATE = 4;

                break;
            case KeyEvent.VK_NUMPAD2:
            case KeyEvent.VK_2:
                RATE = 8;

                break;
            case KeyEvent.VK_NUMPAD3:
            case KeyEvent.VK_3:
                RATE = 12;

                break;
            case KeyEvent.VK_NUMPAD4:
            case KeyEvent.VK_4:
                RATE = 16;

                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10); // 控制子弹移动速度
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
