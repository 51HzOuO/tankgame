package com.teng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;

public class Panel_ extends JPanel implements KeyListener, Runnable {
    public Fire fire = null;
    Random random = new Random();
    MyTank myTank;
    List<Fire> enemyBullets = new Vector<>();
    List<EnemyTank> enemyTanks = new Vector<>();
    List<Fire> bullets = new Vector<>();
    List<Bomb> bombs = new Vector<>();
    Image[] images = new Image[36];
    int enemyTankCount = 10;
    // private int x = 0;
    // private int y = 0;
    // private int direct = 0;
    int originBulletLimit = 3;
    int killCount = 0;
    int bulletLimit = originBulletLimit + killCount;
    // }
    Color_[] values = Color_.values();
    private int RATE = 4;

    {
        for (int i = 0; i < 36; i++) {
            images[i] = Toolkit.getDefaultToolkit().getImage("pic/ht/" + (i + 1) + ".png");
        }
    }

    public Panel_() {
        this.setBackground(Color.GRAY);

        myTank = new MyTank(100, 100, 0, this);
        for (int i = 0; i < enemyTankCount; i++) {
            enemyTanks.add(new EnemyTank(random.nextInt(1100), random.nextInt(700), random.nextInt(4), this));
        }

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

    public void addBullet(Fire bullet) {
        bullets.add(bullet);

    }

    public void addEnemyBullet(Fire bullet) {
        enemyBullets.add(bullet);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color_[] values = Color_.values();
        g.setFont(new java.awt.Font("宋体", 1, 30));
        g.drawString("wasd移动  1,2,3,4变速", 800, 50);
        g.drawString("j发射子弹", 800, 100);
        g.drawString("当前可发射子弹数：" + (bulletLimit - bullets.size()) + "/" + originBulletLimit, 800, 150);
        g.drawString("EXTRA BULLET:" + killCount / 2, 800, 200);
        g.drawString("KILL COUNT:" + killCount, 800, 250);
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
        if (myTank.isLive) drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0, Color_.RED);
        else {
            g.setFont(new java.awt.Font("宋体", 1, 100));
            g.drawString("GAMEOVER", 300, 300);
        }
        // drawTank(enemyTanks.get(0).getX(), enemyTanks.get(0).getY(), g,
        // enemyTanks.get(0).getDirect(), 0, Color_.BLACK);
        // drawTank(enemyTanks.get(1).getX(), enemyTanks.get(1).getY(), g,
        // enemyTanks.get(1).getDirect(), 0, Color_.BLACK);
        // drawTank(enemyTanks.get(2).getX(), enemyTanks.get(2).getY(), g,
        // enemyTanks.get(2).getDirect(), 0, Color_.BLACK);
        int index = -1;
        for (EnemyTank enemyTank : enemyTanks) {
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1, Color_.BLACK);
            } else {
                index = enemyTanks.indexOf(enemyTank);
            }
        }
        if (index != -1) {
            enemyTanks.remove(index);
        }
        for (Fire bullet : bullets) {
            bullet.draw(g);
        }

        for (Fire bullet : enemyBullets) {
            bullet.draw(g);
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.isLive) {
                bomb.draw(g);
            } else {
                bombs.remove(bomb);
            }
        }

        // drawTank(200, 0, g, 1, 1, values[random.nextInt(values.length)]);
        // drawTank(300, 0, g, 1, 1, values[random.nextInt(values.length)]);
        // drawTank(400, 0, g, 1, 1, values[random.nextInt(values.length)]);
        // repaint();
    }

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
                if (type == 0) {
                    g2d.setColor(getColor(values[random.nextInt(values.length)]));
                }
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
                if (type == 0) {
                    g2d.setColor(getColor(values[random.nextInt(values.length)]));
                }
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
                if (type == 0) {
                    g2d.setColor(getColor(values[random.nextInt(values.length)]));
                }
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
                if (type == 0) {
                    g2d.setColor(getColor(values[random.nextInt(values.length)]));
                }
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
        if (!myTank.isLive) {
            return;
        }
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
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Iterator<Fire> bulletIterator = bullets.iterator();
            while (bulletIterator.hasNext()) {
                Fire bullet = bulletIterator.next();
                Iterator<EnemyTank> tankIterator = enemyTanks.iterator();

                while (tankIterator.hasNext()) {
                    EnemyTank enemyTank = tankIterator.next();
                    if (hitTank(bullet, enemyTank)) {
                        // 移除击中的敌方坦克
                        killCount++;
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bomb.setP(this);
                        bombs.add(bomb);
                        // tankIterator.remove();
                        // 移除我方子弹
                        bullet.isLive = false;

                        // 敌方坦克的其他逻辑
                        enemyTank.isLive = false;

                        System.out.println("功德+1");
                        bulletLimit = originBulletLimit + killCount;
                        break; // 跳出内层循环，继续检查下一个子弹
                    }
                }
            }
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                for (int j = 0; j < enemyBullets.size(); j++) {
                    Fire bullet = enemyBullets.get(j);
                    if (hitTank(bullet, myTank)) {
                        // 移除击中的敌方坦克
                        myTank.isLive = false;
                        Bomb bomb = new Bomb(myTank.getX(), myTank.getY());
                        bomb.setP(this);
                        bombs.add(bomb);
                    }
                }
            }
            repaint();

            // for (Fire bullet : bullets) {
            // Iterator<EnemyTank> iterator = enemyTanks.iterator();
            // while (iterator.hasNext()) {
            // EnemyTank enemyTank = iterator.next();
            // if (hitTank(bullet, enemyTank)) {
            // iterator.remove();
            // bullet.isLive = false;// 我方子弹的线程
            // enemyTank.isLive = false;// 敌方坦克的射击线程
            // killCount++;
            // Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
            // bomb.setP(this);
            // bombs.add(bomb);
            // System.out.println("功德+1");
            // bulletLimit = originBulletLimit + killCount;
            // break;
            // }
            // }
            // // for (EnemyTank enemyTank : enemyTanks) {
            // // if (hitTank(bullet, enemyTank)) {
            // // enemyTanks.remove(enemyTank);
            // // bullets.remove(bullet);
            // // bullet.isLive = false;
            // // killCount++;
            // // bulletLimit = originBulletLimit + killCount;
            // // break;
            // // }
            // // }
            // }
            // repaint();

        }
    }

    public boolean hitTank(Fire bullet, Tank tank) {
        switch (tank.getDirect()) {
            case 0:
            case 2:
                if (bullet.x > tank.getX() && bullet.x < tank.getX() + 70 && bullet.y > tank.getY() && bullet.y < tank.getY() + 100) {
                    return true;
                }
                break;
            case 1:
            case 3:
                if (bullet.x > tank.getX() && bullet.x < tank.getX() + 100 && bullet.y > tank.getY() && bullet.y < tank.getY() + 70) {
                    return true;
                }
                break;
        }
        return false;
    }
}
