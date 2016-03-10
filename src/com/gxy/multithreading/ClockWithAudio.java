package com.gxy.multithreading;

import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 带音频的时钟
 *
 * 该实例创建了一个applet来显示一个正在运行的时钟，并且每隔一分钟报时一次。
 *
 * 为了报时，applet播放三个音频剪辑，第一个剪辑包一小时，第二个报分钟，第三个报AM或PM。
 * 所有音频文件都存储在audio目录中，该目录是applet类目录的子目录。
 * 用来报小时的12个音频文件都存储在文件hour1.au、hour1.au，以此类推，知道hour11.au中。
 * 60个用来报分钟的音频文件都存储在文件minute0.au,minute1.au，以此类推，知道minute59.au中。
 * 两个用来报AM和PM的音频文件存储在文件am.au和pm.au中。
 *
 * 需要在独立的线程中播放三个音频剪辑来避免动画的延迟。为了解释这个问题，首先编写一个不在独立线程中播放音频的程序。
 *
 * Created by gxy on 2016/3/6.
 */
public class ClockWithAudio extends JApplet{
    protected AudioClip[] hourAudio = new AudioClip[12];
    protected AudioClip[] minuteAudio = new AudioClip[60];

    protected AudioClip amAudio = Applet.newAudioClip(this.getClass().getResource("com/gxy/multithreading/audio/am.au"));
    protected AudioClip pmAudio = Applet.newAudioClip(this.getClass().getResource("com/gxy/multithreading/audio/pm.au"));

    private StillClock clock = new StillClock();

    private Timer timer = new Timer(1000, new TimerListener());

    private JLabel jlblDigitTime = new JLabel("", JLabel.CENTER);

    public void init() {
        //Create audio clips for pronouncing hours
        for (int i = 0; i < 12; i++) {
            hourAudio[i] = Applet.newAudioClip(this.getClass().getResource("com/gxy/multithreading/audio/hour" + i + ".au"));
        }

        for (int i = 0; i < 60; i++) {
            minuteAudio[i] = Applet.newAudioClip(this.getClass().getResource("com/gxy/multithreading/audio/minute" + i + ".au"));
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

        public void announceTime(int hour, int minute) {
            hourAudio[hour % 12].play();
            try {
                Thread.sleep(1500);

                minuteAudio[minute].play();

                Thread.sleep(1500);
            } catch(InterruptedException ex) {
            }

            if (hour < 12) {
                amAudio.play();
            }
            else
                pmAudio.play();
        }
    }

}
