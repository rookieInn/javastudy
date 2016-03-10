package com.gxy.multithreading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gxy on 2016/3/10.
 */
public class SwingWorkerDemo extends JApplet {
    private JButton jbtComputeWithSwingWorker = new JButton("Compute");
    private JTextField jtfLimit1 = new JTextField(8);
    private JTextField jtfResult1 = new JTextField(6);
    private JButton jbtCompute = new JButton("Compute");
    private JTextField jtfLimit2 = new JTextField(8);
    private JTextField jtfResult2 = new JTextField(6);

    public SwingWorkerDemo() {
        JPanel panel1 = new JPanel(new GridLayout(2, 1));
        panel1.setBorder(BorderFactory.createTitledBorder("Using SwingWorker"));
        JPanel panel11 = new JPanel();
        panel11.add(new JLabel("The number of prime number <= "));
        panel11.add(jtfLimit1);
        panel11.add(new JLabel("is"));
        panel11.add(jtfResult1);
        JPanel panel12 = new JPanel();
        panel12.add(jbtComputeWithSwingWorker);
        panel1.add(panel11);
        panel1.add(panel12);

        JPanel panel2 = new JPanel(new GridLayout(2, 1));
        panel2.setBorder(BorderFactory.createTitledBorder("Without Using SwingWorker"));
        JPanel panel21 = new JPanel();
        panel21.add(new JLabel("The number of prime number <= "));
        panel21.add(jtfLimit2);
        panel21.add(new JLabel("is"));
        panel21.add(jtfResult2);
        JPanel panel22 = new JPanel();
        panel22.add(jbtCompute);
        panel2.add(panel21);
        panel2.add(panel22);

        setLayout(new GridLayout(1, 2));
        add(panel1);
        add(panel2);

        jbtComputeWithSwingWorker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ComputePrime((Integer.parseInt(jtfLimit1.getText())), jtfResult1).execute();
            }
        });

        jbtCompute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = ComputePrime.getNumOfPrimes(Integer.parseInt(jtfLimit2.getText()));
                jtfResult2.setText(count + "");
            }
        });
    }

    static class ComputePrime extends SwingWorker<Integer, Object> {
        private int limit;
        private JTextField result;

        public ComputePrime(int limit, JTextField result) {
            this.limit = limit;
            this.result = result;
        }

        @Override
        protected Integer doInBackground() throws Exception {
            return getNumOfPrimes(limit);
        }

        @Override
        protected void done() {
            try {
                result.setText(get().toString());
            } catch (Exception ex) {
                result.setText(ex.getMessage());
            }
        }

        public static int getNumOfPrimes(int limit) {
            int count = 0;
            int number = 2;

            while (number <= limit) {
                if (isPrime(number)) {
                    count++;
                }
                number++;
            }
            return count;
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
