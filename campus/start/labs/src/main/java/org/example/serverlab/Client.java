package org.example.serverlab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
            Socket socket = new Socket("localhost", 3000);
            OutputStream outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            String massage = scanner.nextLine();
            outputStream.write(massage.getBytes());
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[100];
            int massageLength = inputStream.read(buffer);
            System.out.println(new String(buffer, 0, massageLength));
    }
}
