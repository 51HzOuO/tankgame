package com.teng;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.Vector;

// 继承Tank父类
public class EnemyTank extends Tank implements Runnable {

    static List<EnemyTank> enemyTanks;
    Vector<Fire> fires = new Vector<Fire>();
    boolean isLive = true;
    Panel_ p = (Panel_) panel;

    public EnemyTank(int x, int y, int direct, JPanel panel) {
        super(x, y, direct, panel);
        new Thread(this).start();
    }

    public static void setEnemyTanks(List<EnemyTank> enemyTanks) {
        EnemyTank.enemyTanks = enemyTanks;
    }

    // 来自Runnable接口
    @Override
    public void run() {
        Random random = new Random();
        while (isLive) {

            int flag = 0;
            for (EnemyTank enemyTank : enemyTanks) {
                if (enemyTank == this)
                    flag++;
            }
            if (flag == 0) {
                isLive = false;
            }

            try {

                // 如果随机数小于3， 那就在4里面随机一个数作为方向
                if (random.nextInt(10) < 3) {
                    direct = random.nextInt(4);
                }
                switch (direct) {
                    case 0:
                        moveUp(1);
                        break;
                    case 1:
                        moveRight(1);
                        break;
                    case 2:
                        moveDown(1);
                        break;
                    case 3:
                        moveLeft(1);
                        break;
                }

                Thread.sleep(random.nextInt(500) + 500);
                if (!isLive)
                    break;
                if (random.nextInt(10) <= 2) {
                    fire();
                }
                Thread.sleep(random.nextInt(500) + 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        enemyTanks.remove(this);
    }

    @Override
    public void fire() {
        Fire bullet;
        // 实现特定于 EnemyTank 的射击逻辑
        // 例如，EnemyTank 可以在特定条件下自动射击
        // 这里只是一个简单的实现示例
        switch (direct) {
            case 0:
                bullet = new Fire(x + 35, y, direct);
                break;
            case 1:
                bullet = new Fire(x + 95, y + 35, direct);
                break;
            case 2:
                bullet = new Fire(x + 35, y + 95, direct);
                break;
            case 3:
                bullet = new Fire(x, y + 35, direct);
                break;
            default:
                return;
        }
        bullet.setPanel(this.panel);
        if (panel instanceof Panel_) {
            ((Panel_) panel).addEnemyBullet(bullet);
        }
        new Thread(bullet).start();
    }

    // 可以添加更多 EnemyTank 特有的方法和属性
    // 例如 AI 控制的逻辑等
}
