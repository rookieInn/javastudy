package com.gxy.multithreading;

import javax.swing.*;

/**
 * 闪烁文本
 * Created by gxy on 2016/3/6.
 */
public class FlashingText extends JApplet implements Runnable {
    private JLabel jlblText = new JLabel("Welcome", JLabel.CENTER);

    public FlashingText() {
        add(jlblText);
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            if (jlblText.getText() == null)
                jlblText.setText("Welcome");
            else
                jlblText.setText(null);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
            }
        }
    }
}
