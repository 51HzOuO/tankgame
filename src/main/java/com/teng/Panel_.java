package com.teng;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import static com.teng.Tank.getTankRectangle;

public class Panel_ extends JPanel implements KeyListener, Runnable {
    public Fire fire = null;
    Random random = new Random();
    EnemyTankInfo enemyTankInfo = null;
    MyTank myTank;
    int IronPlateCount = random.nextInt(8) + 3;
    List<Fire> enemyBullets = new Vector<>();
    List<IronPlate> ironPlates = new Vector<>();
    List<EnemyTank> enemyTanks = new Vector<>();
    List<Fire> bullets = new Vector<>();
    List<Bomb> bombs = new Vector<>();
    Image[] images = new Image[36];
    // 设定10个敌方坦克
    int enemyTankCount = 5;
    // 初始限制1个子弹
    int originBulletLimit = 2;
    // private int x = 0;
    // private int y = 0;
    // private int direct = 0;
    int killCount = 0;
    // 杀的越多子弹数量越多？
    int bulletLimit = originBulletLimit;
    // 包含所有颜色的数组
    Color_[] values = Color_.values();
    int runTimes = random.nextInt(2500) + 2000;
    // 移动速度
    private int RATE = 1;

    {
        Tank.setPanel_(this);
    }

    // 动图字典
    {
        for (int i = 0; i < 36; i++) {
            images[i] = Toolkit.getDefaultToolkit().getImage("pic/ht/" + (i + 1) + ".png");
        }
    }

    // 初始化panel
    public Panel_(boolean again, EnemyTankInfo enemyTankInfo) {

        EnemyTank.setEnemyTanks(enemyTanks);
        SimpleAudioPlayer music = new SimpleAudioPlayer("src\\km" + (random.nextInt(4) + 1) + ".wav");
        new Thread(music).start();
        this.setBackground(Color.GRAY);
        initIronPlates();
        // 自己坦克的出生位置
        myTank = new MyTank(100, 100, 0, this);
        if (!again) {
            for (int i = 0; i < enemyTankCount; ) {
                int x = random.nextInt(1100);
                int y = random.nextInt(700);
                int direct = random.nextInt(4);

                EnemyTank newTank = new EnemyTank(x, y, direct, this);
                if (!isOverlapWithOtherTanks(newTank)) {
                    enemyTanks.add(newTank);
                    i++;
                }
            }
        } else {
            enemyTanks.clear();
            int[] x = enemyTankInfo.x;
            int[] y = enemyTankInfo.y;
            int[] direct = enemyTankInfo.direct;
            for (int i = 0; i < enemyTankInfo.nums; i++) {
                enemyTanks.add(new EnemyTank(x[i], y[i], direct[i], this));
            }
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

    private void initIronPlates() {
        for (int i = 0; i < IronPlateCount; i++) {
            int x = random.nextInt(800);
            int y = random.nextInt(600);
            int width;
            if (Math.random() > 0.5) {
                width = Math.random() > 0.5 ? 20 : 100;
            } else {
                width = Math.random() > 0.5 ? 60 : 60;
            }
            IronPlate ironPlate = new IronPlate(x, y, width, 120 - width);
            ironPlates.add(ironPlate);
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

    private boolean isOverlapWithOtherTanks(EnemyTank newTank) {
        Rectangle newTankRect = getTankRectangle(newTank);
        Rectangle myTankRect = getTankRectangle(myTank);

        if (newTankRect.intersects(myTankRect)) {
            return true;
        }

        for (Tank tank : enemyTanks) {
            Rectangle existingTankRect = getTankRectangle(tank);
            if (newTankRect.intersects(existingTankRect)) {
                return true;
            }
        }

        return false;
    }

    public void addBullet(Fire bullet) {
        bullets.add(bullet);

    }

    public void addEnemyBullet(Fire bullet) {
        enemyBullets.add(bullet);

    }

    // 来自java.awt.Component类
    // 当需要自定义绘制AWT组件的方法时，会重写paint
    @Override
    public void paint(Graphics g) {
        // 保留父类的绘制行为
        super.paint(g);

    }

    // private List<Fire> bullets = new ArrayList<>();

    // 来自javax.swing.JComponent类
    // 自定义绘制Swing组件的方法
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color_[] values = Color_.values();
        g.setFont(new Font("宋体", 1, 20));
        g.drawString("WASD移动", 800, 50);
        g.drawString("j发射子弹", 800, 100);
        g.drawString("当前可发射子弹数：" + (originBulletLimit + killCount / 2 - bullets.size()) + "/" + bulletLimit, 800, 150);
        g.drawString("EXTRA BULLET:" + killCount / 2, 800, 200);
        g.drawString("KILL COUNT:" + killCount, 800, 250);
        g.setColor(getColor(values[random.nextInt(values.length)]));
        g.drawString("" + runTimes, 10, 50);

        /*
        // 使用Graphics2D绘制更复杂的图像
        // Graphics2D是Graphics的拓展
         Graphics2D g2d = (Graphics2D) g.create();
         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
         g2d.setColor(setColor());
         g2d.fillRect(0, 0, 20, 100);
         g2d.fillRect(50, 0, 20, 100);

         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
         g2d.fillRect(20, 20, 30, 60);

         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
         g2d.fillOval(20, 50 - 15, 30, 30);
         g2d.fillRect(35 - 2, 0, 4, 50);

         g2d.dispose(); // Dispose of this graphics context and release any system
         resources that it is
         // using
         */

        // 出生位置的图片
        Image image = Toolkit.getDefaultToolkit().getImage("pic/bj.jpg");


        g.drawImage(image, 100, 100, 108, 108, this);

        // moveTank();

        // 当坦克还活着则绘制坦克
        // 如果if后面只有一个语句可以省略中括号
        if (myTank.isLive) drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 0, Color_.RED);
        else {
            g.setFont(new Font("宋体", 1, 100));
            g.drawString("GAMEOVER", 300, 300);
        }

        // drawTank(enemyTanks.get(0).getX(), enemyTanks.get(0).getY(), g,
        // enemyTanks.get(0).getDirect(), 0, Color_.BLACK);
        // drawTank(enemyTanks.get(1).getX(), enemyTanks.get(1).getY(), g,
        // enemyTanks.get(1).getDirect(), 0, Color_.BLACK);
        // drawTank(enemyTanks.get(2).getX(), enemyTanks.get(2).getY(), g,
        // enemyTanks.get(2).getDirect(), 0, Color_.BLACK);


        int index = -1;
        //for-each循环
        for (EnemyTank enemyTank : enemyTanks) {

            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1, Color_.BLACK);

        }
        // 如果列表中的坦克已经死亡，则根据index从列表中删除掉

        // 绘制每一个子弹
        for (Fire bullet : bullets) {
            bullet.draw(g);
        }
        // 绘制敌方子弹
        for (Fire bullet : enemyBullets) {
            bullet.draw(g);
        }

        for (IronPlate ironPlate : ironPlates) {
            g.setColor(Color.DARK_GRAY); // 设置铁板颜色
            g.fillRect(ironPlate.getX(), ironPlate.getY(), ironPlate.getWidth(), ironPlate.getHeight());
        }
        // ？绘制死亡动画？
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

    public void showInfo(Graphics g) {

    }

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

                // 两个轮胎
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g2d.fill3DRect(x, y, 20, 100, false);
                g2d.fill3DRect(x + 50, y, 20, 100, false);

                // 车身
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.fill3DRect(x + 20, y + 20, 30, 60, false);

                // 炮塔
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
                g2d.fillOval(x + 20, y + 35, 30, 30);
                if (type == 0) {
                    g2d.setColor(getColor(values[random.nextInt(values.length)]));
                }
                // 炮管
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

    // 来自JFrame的keylistener接口
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 来自JFrame的keylistener接口
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
                RATE = 1;

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

    // 来自JFrame的keylistener接口
    @Override
    public void keyReleased(KeyEvent e) {

    }

    // 来自Runnable接口

    private void career() throws IOException {
        File file = new File("src\\main\\resources\\career.dat");
        boolean theFirstTime = false;
        if (!file.exists()) {
            file.createNewFile();
            theFirstTime = true;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(file, true)));
        String line = null;
        String temp;
        while ((temp = br.readLine()) != null) {
            line = temp;
        }
        if (theFirstTime) {
            line = "0";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String winOrLose = myTank.isLive ? "WIN" : "LOSE";
        ps.println((Integer.parseInt(line.split("\\|")[0]) + 1) + "|" + winOrLose + "|" + killCount + "/" + enemyTankCount + "|" + dtf.format(now));
        ps.close();
        br.close();
    }

    public boolean hitIronPlate(Fire bullet) {
        Rectangle bulletRect = new Rectangle(bullet.x, bullet.y, 5, 5);
        for (IronPlate ironPlate : ironPlates) {
            if (bulletRect.intersects(ironPlate.getRectangle())) {
                return true; // 子弹击中铁板
            }
        }
        return false; // 子弹未击中铁板
    }

    @Override
    public void run() {

        while (true) {
            runTimes--;
            if (runTimes == 0) {
                runTimes = random.nextInt(2500) + 2000;
                enemyTankCount++;
                for (; ; ) {
                    int x = random.nextInt(1100);
                    int y = random.nextInt(700);
                    int direct = random.nextInt(4);
                    EnemyTank newTank = new EnemyTank(x, y, direct, this);
                    if (!isOverlapWithOtherTanks(newTank)) {
                        enemyTanks.add(newTank);
                        break;
                    }
                }

            }
            if (enemyTanks.size() == 0) {
                try {
                    career();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new File("src/main/resources/panel.ser").delete();
                JOptionPane.showMessageDialog(null, "你赢了");
                System.exit(0);
            }
            if (!myTank.isLive) {
                try {
                    career();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new File("src/main/resources/panel.ser").delete();
                JOptionPane.showMessageDialog(null, "你输了");
                System.exit(0);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Fire[] bullets = new Fire[this.bullets.size()];
            int i1 = 0;
            for (Fire bullet : bullets) {
                // 检查子弹是否击中铁板
                if (hitIronPlate(bullet)) {
//                    bullet.isLive = false; // 销毁子弹
                    bullets[i1++] = bullet;
                    continue; // 继续检查下一颗子弹
                }

                for (EnemyTank enemyTank : enemyTanks) {
                    if (hitTank(bullet, enemyTank)) {
                        // 子弹与敌方坦克的碰撞处理
                        killCount++;
                        Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                        bomb.setP(this);
                        bombs.add(bomb);
//                        bullet.isLive = false;
                        bullets[i1++] = bullet;
                        enemyTank.isLive = false;
                        System.out.println("功德+1");
                        bulletLimit = originBulletLimit + killCount / 2;
                        enemyTanks.remove(enemyTank);
                        break; // 跳出内层循环，继续检查下一个子弹
                    }
                }
            }
            for (Fire bullet : bullets) {
                if (bullet != null) {
                    bullet.isLive = false;
                    this.bullets.remove(bullet);
                }
            }

            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                for (int j = 0; j < enemyBullets.size(); j++) {
                    Fire bullet = enemyBullets.get(j);
                    if (hitIronPlate(bullet)) {
                        bullet.isLive = false; // 销毁子弹
                        continue; // 继续检查下一颗子弹
                    }
                    if (hitTank(bullet, myTank)) {
                        // 移除击中的我方坦克
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
