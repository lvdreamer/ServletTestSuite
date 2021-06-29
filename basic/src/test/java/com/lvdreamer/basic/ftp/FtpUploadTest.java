package com.lvdreamer.basic.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.*;

public class FtpUploadTest {
    @Test
    public void uploadTest() {
        try {
            FileInputStream in = new FileInputStream(new File("D:\\data\\a-upload.txt"));
            ReadLimiter readLimiter = new ReadLimiter(in, new StreamLimiter(6250));
            boolean flag = uploadFile("192.168.100.2", 21, "vagrant", "vagrant", "/home/vagrant/ftp", "a-upload.txt", readLimiter);
            System.out.println(flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static boolean uploadFile(String url, int port, String username, String password, String path, String filename, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);//连接FTP服务器
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftp.login(username, password);//登录
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(filename, input);

            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
}
