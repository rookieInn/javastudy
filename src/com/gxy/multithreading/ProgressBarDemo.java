package com.gxy.multithreading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 让用户指定素数的个数n,并且显示从2开始的前n个素数。
 * Created by gxy on 2016/3/10.
 */
public class ProgressBarDemo extends JApplet {
    private JProgressBar jpb = new JProgressBar();
    private JTextArea jtaResult = new JTextArea();
    private JTextField jtfPrimeCount = new JTextField(8);
    private JButton jbtDisplayPrime = new JButton("Display Prime");

    public ProgressBarDemo() {
        jpb.setStringPainted(true); //Paint the percent in a string
        jpb.setValue(0);
        jpb.setMaximum(100);

        jtaResult.setWrapStyleWord(true);
        jtaResult.setLineWrap(true);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter the prime number count"));
        panel.add(jtfPrimeCount);
        panel.add(jbtDisplayPrime);

        add(jpb, BorderLayout.NORTH);
        add(new JScrollPane(jtaResult), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        jbtDisplayPrime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComputePrime task = new ComputePrime(
                        Integer.parseInt(jtfPrimeCount.getText()), jtaResult
                );

                task.addPropertyChangeListener(new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent e) {
                        if ("progress".equals(e.getPropertyName())) {
                            jpb.setValue((Integer)e.getNewValue());
                        }
                    }
                });

                task.execute();
            }
        });
    }

   static class ComputePrime extends SwingWorker<Integer, Integer> {
       private int count;
       private JTextArea result;

       public ComputePrime(int count, JTextArea result) {
           this.count = count;
           this.result = result;
       }

       @Override
       protected Integer doInBackground() throws Exception {
           publishPrimeNumbers(count);
           return 0;
       }

       protected void process(java.util.List<Integer> list) {
           for (int i = 0; i < list.size(); i++) {
               result.append(list.get(i) + " ");
           }
       }

       private void publishPrimeNumbers(int n) {
           int count = 0;
           int number = 2;

           while (count <= n) {
               if (isPrime(number)) {
                   count++;
                   setProgress(100 * count / n);
                   publish(number);
               }

               number++;
           }
       }

       private static boolean isPrime(int number) {
           for (int divisor = 2; divisor <= number /2; divisor++) {
               if (number % divisor ==0) {
                   return false;
               }
           }

           return true;
       }
   }

}
