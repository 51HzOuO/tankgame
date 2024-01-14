package com.teng;

import javax.swing.JFrame;

public class Windows extends JFrame {

    Panel_ panel = new Panel_();

    // public void move(String key) {
    // panel.moveTank(key);
    // panel.repaint();
    // }

    public Windows() {
        this.setSize(1200, 800);
        new Thread(panel).start();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
        this.addKeyListener(panel);
    }
}
