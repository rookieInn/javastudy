package com.gxy.multithreading;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gxy on 2016/3/6.
 */
public class ClockWithAudioOnSeparateThread extends JApplet {
    protected AudioClip[] hourAudio = new AudioClip[12];
    protected AudioClip[] minuteAudio = new AudioClip[60];

    protected AudioClip amAudio = Applet.newAudioClip(this.getClass().getResource("audio/am.au"));
    protected AudioClip pmAudio = Applet.newAudioClip(this.getClass().getResource("audio/pm.au"));

    private StillClock clock = new StillClock();

    private Timer timer = new Timer(1000, new TimerListener());

    private JLabel jlblDigitTime = new JLabel("", JLabel.CENTER);

    public void init() {
        //Create audio clips for pronouncing hours
        for (int i = 0; i < 12; i++) {
            hourAudio[i] = Applet.newAudioClip(this.getClass().getResource("audio/hour" + i + ".au"));
        }

        for (int i = 0; i < 60; i++) {
            minuteAudio[i] = Applet.newAudioClip(this.getClass().getResource("audio/minute" + i + ".au"));
        }

        add(clock, BorderLayout.CENTER);
        add(jlblDigitTime, BorderLayout.SOUTH);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clock.setCurrentTime();
            clock.repaint();
            jlblDigitTime.setText(clock.getHour() + ":" + clock.getMinute()
                    + ":" + clock.getSecond());
            if (clock.getSecond() == 0)
                announceTime(clock.getHour(), clock.getMinute());
        }

    }

    public void announceTime(int h, int m) {
        new Thread(new AnnounceTimeOnSeparateThread(h, m)).start();
    }

    class AnnounceTimeOnSeparateThread implements Runnable {
        private int hour, minute;

        public AnnounceTimeOnSeparateThread(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        @Override
        public void run() {
            hourAudio[hour % 12].play();

            try {
                Thread.sleep(1500);

                minuteAudio[minute].play();

                Thread.sleep(1500);

                if (hour < 12) {
                    amAudio.play();
                } else
                    pmAudio.play();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        ClockWithAudioOnSeparateThread applet = new ClockWithAudioOnSeparateThread();
        applet.init();
        applet.start();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("ClockWithAudio");
        frame.getContentPane().add(applet, BorderLayout.CENTER);
        frame.setSize(400, 320);
        frame.setVisible(true);
    }
}
