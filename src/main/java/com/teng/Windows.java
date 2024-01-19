package com.teng;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Windows extends JFrame {

    Panel_ panel;

    String name;
    boolean again = false;
    EnemyTankInfo enemyTankInfo;

    //Windows是一个构造函数
    public Windows(String name) {
        this.name = name;

    }

    public void again(int killCount, int myTankX, int myTankY, int myTankDirect) {
        panel.killCount = killCount;
        panel.myTank.x = myTankX;
        panel.myTank.y = myTankY;
        panel.myTank.direct = myTankDirect;
    }
    // public void move(String key) {
    // panel.moveTank(key);
    // panel.repaint();
    // }

    public Panel_ getPanel() {
        return panel;
    }

    public void go() {
        panel = new Panel_(again, enemyTankInfo);
        this.setSize(1200, 800);
        new Thread(panel).start();
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
        this.addKeyListener(panel);
        this.setTitle(name);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 在这里添加关闭前需要执行的代码
                try {
                    save();
//                    System.out.println("非正常结束，已保存进度");
                    Windows.this.dispose();
                    JOptionPane.showMessageDialog(null, "非正常结束，已保存进度", "非正常结束", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }

    public void save() throws IOException {
        if (!(panel.killCount == panel.enemyTankCount) && panel.myTank.isLive) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src/main/resources/panel.ser")))) {

                //MyTank
                oos.writeInt(panel.killCount);
                oos.writeInt(panel.myTank.x);
                oos.writeInt(panel.myTank.y);
                oos.writeInt(panel.myTank.direct);

                //EnemyTank
                List<EnemyTank> enemyTanks = panel.enemyTanks;
                EnemyTankInfo info = new EnemyTankInfo(enemyTanks.size());
                for (EnemyTank enemyTank : enemyTanks) {
                    info.setInfo(enemyTank.x, enemyTank.y, enemyTank.direct);
                }
                oos.writeObject(info);
            }
        }
    }
}
