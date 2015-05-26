package com.marvin.study.socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * <strong><br><br></strong>
 *
 * @author gezhiqiang
 * @since 2015-03-17 14:50
 */
public class Server {

    private ServerSocket server;
    private Socket socket;

    public void listen() throws IOException {
        server = new ServerSocket(8888);
        System.out.println("开始监听...");
        while (true) {
            socket = server.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("you input is : " + br.readLine());
            System.out.println("结束监听...");
        }

    }

    public static void main(String[] args) {
        try {
            new Server().listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
