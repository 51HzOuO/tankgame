package com.teng;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int choice = JOptionPane.showOptionDialog(null, "欢迎来到坦克大战", "坦克大战", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"新游戏", "读档"}, null);

        Windows tankGame = new Windows("Tank Game");
        if (choice == 1 && new File("src/main/resources/panel.ser").exists()) {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("src/main/resources/panel.ser")));
            int killCount = ois.readInt();
            int myTankX = ois.readInt();
            int myTankY = ois.readInt();
            int myTankDirect = ois.readInt();
            EnemyTankInfo enemyTankInfo = (EnemyTankInfo) ois.readObject();
            JOptionPane.showMessageDialog(null, "读档成功，点击确定将在3秒后继续游戏", "读档成功", JOptionPane.INFORMATION_MESSAGE);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankGame.again = true;
            tankGame.enemyTankInfo = enemyTankInfo;
            tankGame.go();
            tankGame.again(killCount, myTankX, myTankY, myTankDirect);

        } else {
            if (choice == 1) {
                JOptionPane.showMessageDialog(null, "读档失败，未存档，点击确定开始新游戏", "读档失败", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "点击确定开始游戏", "新游戏", JOptionPane.INFORMATION_MESSAGE);
            }
            tankGame.go();
        }
    }

}
