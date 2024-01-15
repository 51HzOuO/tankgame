package com.teng;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

public class Bomb {
    int x, y;
    int life = 36;
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() {
        life--;
        if (life == 0) {
            isLive = false;
        }
    }

    Panel_ p;

    public void setP(Panel_ p) {
        this.p = p;
    }

    int i = 0;

    Random random = new Random();
    int times = random.nextInt(3) + 1;

    int speed = random.nextInt(5) + 1;

    public void draw(Graphics g) {
        Image[] images = p.images;

        // g.drawImage(images[36-life], x, y, 60, 60, null);
        switch (life) {
            case 36:
                g.drawImage(images[0], x, y, 60, 60, null);
                break;
            case 35:
                g.drawImage(images[1], x, y, 60, 60, null);
                break;
            case 34:
                g.drawImage(images[2], x, y, 60, 60, null);
                break;
            case 33:
                g.drawImage(images[3], x, y, 60, 60, null);
                break;
            case 32:
                g.drawImage(images[4], x, y, 60, 60, null);
                break;
            case 31:
                g.drawImage(images[5], x, y, 60, 60, null);
                break;
            case 30:
                g.drawImage(images[6], x, y, 60, 60, null);
                break;
            case 29:
                g.drawImage(images[7], x, y, 60, 60, null);
                break;
            case 28:
                g.drawImage(images[8], x, y, 60, 60, null);
                break;
            case 27:
                g.drawImage(images[9], x, y, 60, 60, null);
                break;
            case 26:
                g.drawImage(images[10], x, y, 60, 60, null);
                break;
            case 25:
                g.drawImage(images[11], x, y, 60, 60, null);
                break;
            case 24:
                g.drawImage(images[12], x, y, 60, 60, null);
                break;
            case 23:
                g.drawImage(images[13], x, y, 60, 60, null);
                break;
            case 22:
                g.drawImage(images[14], x, y, 60, 60, null);
                break;
            case 21:
                g.drawImage(images[15], x, y, 60, 60, null);
                break;
            case 20:
                g.drawImage(images[16], x, y, 60, 60, null);
                break;
            case 19:
                g.drawImage(images[17], x, y, 60, 60, null);
                break;
            case 18:
                g.drawImage(images[18], x, y, 60, 60, null);
                break;
            case 17:
                g.drawImage(images[19], x, y, 60, 60, null);
                break;
            case 16:
                g.drawImage(images[20], x, y, 60, 60, null);
                break;
            case 15:
                g.drawImage(images[21], x, y, 60, 60, null);
                break;
            case 14:
                g.drawImage(images[22], x, y, 60, 60, null);
                break;
            case 13:
                g.drawImage(images[23], x, y, 60, 60, null);
                break;
            case 12:
                g.drawImage(images[24], x, y, 60, 60, null);
                break;
            case 11:
                g.drawImage(images[25], x, y, 60, 60, null);
                break;
            case 10:
                g.drawImage(images[26], x, y, 60, 60, null);
                break;
            case 9:
                g.drawImage(images[27], x, y, 60, 60, null);
                break;
            case 8:
                g.drawImage(images[28], x, y, 60, 60, null);
                break;
            case 7:
                g.drawImage(images[29], x, y, 60, 60, null);
                break;
            case 6:
                g.drawImage(images[30], x, y, 60, 60, null);
                break;
            case 5:
                g.drawImage(images[31], x, y, 60, 60, null);
                break;
            case 4:
                g.drawImage(images[32], x, y, 60, 60, null);
                break;
            case 3:
                g.drawImage(images[33], x, y, 60, 60, null);
                break;
            case 2:
                g.drawImage(images[34], x, y, 60, 60, null);
                break;
            case 1:
                g.drawImage(images[35], x, y, 60, 60, null);
                break;
            // System.out.println("error" + life);
        }
        if (life == 1 && times != 0) {
            times--;
            life = 36;
        }

        // if (life == 1 && times-- != 0) {
        // life = 36;
        // }

        // System.out.println(life);
        if (i++ == speed) {
            lifeDown();
            i = 0;
        }
    }
}
