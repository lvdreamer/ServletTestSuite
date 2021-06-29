package com.lvdreamer.httptest.service;

import java.io.*;
import java.net.Socket;

public class SocketServer implements Runnable {
    Socket s;

    public SocketServer(Socket s) {
        this.s = s;
    }

    //线程
    public void run() {
        OutputStream os = null;
        InputStream in = null;
        try {
            in = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String requestHeader;
            requestHeader = br.readLine();
            int begin = requestHeader.indexOf("/") + 1;
            int end = requestHeader.indexOf("HTTP/");
            String url = "2222";
            url = url + requestHeader.substring(begin, end);


            os = s.getOutputStream();
            os.write("HTTP/1.1 200 OK\r\n".getBytes());
            os.write("Content-Type:text/html;charset=utf-8\r\n".getBytes());
            os.write("\r\n".getBytes());

            File f = new File(url);
            if (f.exists())
            {
                FileInputStream fin = new FileInputStream(f);
                byte[] b = new byte[1024];
                int len;
                while ((len = fin.read(b)) != -1) {
                    os.write(b, 0, len);
                }
            }
            os.flush();
            os.close();

        } catch (Exception e) {

        }

    }
}