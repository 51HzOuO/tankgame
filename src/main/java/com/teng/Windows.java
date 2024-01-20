package com.teng;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.File;
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
                    int dialogResult = JOptionPane.showConfirmDialog(null, "是否需要存档?", "确认", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        Windows.this.dispose();
                        save();
                        JOptionPane.showMessageDialog(null, "非正常结束，已保存进度", "非正常结束", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        Windows.this.dispose();
                        JOptionPane.showMessageDialog(null, "非正常结束，未保存进度", "非正常结束", JOptionPane.INFORMATION_MESSAGE);
                        new File("src/main/resources/panel.ser").delete();

                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }

    public void save() throws IOException {

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
