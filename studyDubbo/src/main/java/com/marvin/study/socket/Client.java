package com.marvin.study.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <strong><br><br></strong>
 *
 * @author gezhiqiang
 * @since 2015-03-17 15:01
 */
public class Client {

    Socket client;

    public void send() throws IOException {
        client = new Socket("127.0.0.1",8888);
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println("哈沙");
    }

    public static void main(String[] args) throws IOException {
        new Client().send();
    }
}
