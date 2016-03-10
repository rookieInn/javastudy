package com.gxy.network;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 服务端
 * 客户端向服务端发送数据。
 * 服务端接收数据，并用它来产生一个结果，然后将结果返回给客户端。
 *
 * Created by gxy on 2016/3/10.
 */
public class Server extends JFrame {
    private JTextArea jta = new JTextArea();

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);

        setTitle("Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("Server started at " + new Date() + '\n');

            Socket socket = serverSocket.accept();

            DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

            while (true) {
                double radius = inputFromClient.readDouble();

                double area = radius * radius * Math.PI;

                outputToClient.writeDouble(area);

                jta.append("Radius received from client:" + radius + '\n');
                jta.append("Area found: " + area + '\n');
            }
        } catch(IOException e) {
            System.err.println(e);
        }
    }
}
