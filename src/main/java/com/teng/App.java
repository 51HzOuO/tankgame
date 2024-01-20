package com.teng;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int choice = JOptionPane.showOptionDialog(null, "欢迎来到坦克大战", "坦克大战", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"新游戏", "读档", "查看战绩"}, null);
        Windows tankGame = new Windows("Tank Game");
        if (choice == 2) {
            File file = new File("src\\main\\resources\\career.dat");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "未找到战绩文件", "未找到战绩文件", JOptionPane.INFORMATION_MESSAGE);
                choice = JOptionPane.showOptionDialog(null, "欢迎来到坦克大战", "坦克大战", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"新游戏", "读档"}, null);
            } else {
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] split = line.split("\\|");
                    sb.append("NO:").append(split[0]).append("——").append("FINAL:").append(split[1]).append("——").append("KILLED:").append(split[2]).append("——").append("TIME:").append(split[3]).append("\n");
                }
                br.close();
                JOptionPane.showMessageDialog(null, sb.toString(), "战绩", JOptionPane.INFORMATION_MESSAGE);
                choice = JOptionPane.showOptionDialog(null, "欢迎来到坦克大战", "坦克大战", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"新游戏", "读档"}, null);
            }
        }
        if (choice == JOptionPane.CLOSED_OPTION) {
            // 用户点击了关闭按钮
            System.exit(0); // 或者执行任何你希望进行的操作
        }
        if (choice == 1 && new File("src/main/resources/panel.ser").exists()) {
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("src/main/resources/panel.ser")));
            int killCount = ois.readInt();
            int myTankX = ois.readInt();
            int myTankY = ois.readInt();
            int myTankDirect = ois.readInt();
            EnemyTankInfo enemyTankInfo = (EnemyTankInfo) ois.readObject();
            int result = JOptionPane.showConfirmDialog(null, "读档成功，是否立即开始游戏？", "读档成功", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                // 如果用户选择 "是"，则继续游戏
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tankGame.again = true;
                tankGame.enemyTankInfo = enemyTankInfo;
                tankGame.go();
                tankGame.again(killCount, myTankX, myTankY, myTankDirect);
                // ... 这里继续你的游戏逻辑，如恢复游戏状态等
            } else {
                // 如果用户选择 "否" 或关闭对话框，则退出程序
                System.exit(0);
            }

        } else {
            if (choice == 1) {
                int result = JOptionPane.showConfirmDialog(null, "读档失败，未存档，点击OK开始新游戏", "读档失败", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result != JOptionPane.OK_OPTION) {
                    System.exit(0); // 如果用户点击取消或关闭按钮，程序退出
                }
            } else {
                int result = JOptionPane.showConfirmDialog(null, "点击OK开始游戏", "新游戏", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result != JOptionPane.OK_OPTION) {
                    System.exit(0); // 如果用户点击取消或关闭按钮，程序退出
                }
            }
            tankGame.go();
        }
    }
}