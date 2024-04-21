package org.example.serverlab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(3000);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[100];
            int count = inputStream.read(buffer);
            String message = new String(buffer, 0, count);
            OutputStream outputStream = socket.getOutputStream();
            String prefix = "Echo";
            StringBuilder builder = new StringBuilder(prefix);
            builder.append(message);
            outputStream.write(builder.toString().getBytes());
            inputStream.close();
            outputStream.close();
        }
    }
}
