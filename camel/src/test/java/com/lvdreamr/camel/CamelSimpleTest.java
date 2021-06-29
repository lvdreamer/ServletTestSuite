package com.lvdreamr.camel;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class CamelSimpleTest {

    @Test
    public void demo() throws Exception {
        //Camel 的上下文对象
        CamelContext context = new DefaultCamelContext();
        // 添加路由
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/data/camel/source?delay=5s")
                        .to("file:/data/camel/test/");
            }
        });
        //启动
        context.start();
        while (true) {

        }
    }

    @Test
    public void demo2() throws Exception {
        //Camel 的上下文对象
        CamelContext context = new DefaultCamelContext();
        // 添加路由
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/data/camel/source?scheduler=spring&scheduler.cron=0/10 * * * * ?")
                        .to("file:/data/camel/test/");
            }
        });
        //启动
        context.start();
        while (true) {

        }
    }

    @Test
    public void demo3() throws Exception {
        String filePath = "D:\\demo_data";
        String outFileDir = "D:\\demo_data_merge";
        File chkFile = new File(filePath);
        File[] chkFileList = chkFile.listFiles((f, name) -> name.contains("CHK"));
        for (File checkFile : chkFileList) {
            BufferedReader br = new BufferedReader(new FileReader(checkFile));
            String line;
            String spliter = new String(new byte[]{0x01});
            int index = 0;
            File outChkFile = new File(outFileDir + "/" + checkFile.getName());
            BufferedWriter bw = null;
            int lineCount = 0;
            String firstFileName = null;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                String[] split = line.split(spliter, -1);
                String fileName = split[0];
                File avlFile = new File(chkFile.getAbsolutePath() + "/" + fileName);
                if (!avlFile.exists()) {
                    System.out.println("文件不存在，file：" + avlFile);
                }
                if (index == 0) {
                    firstFileName = fileName;
                    bw = new BufferedWriter(new FileWriter(outFileDir + "/" + fileName));
                }
                BufferedReader reader = new BufferedReader(new FileReader(avlFile));
                String dataLine;
                while ((dataLine = reader.readLine()) != null) {
                    bw.write(dataLine);
                    bw.newLine();
                    lineCount++;
                }
                reader.close();
                index++;
            }
            bw.flush();
            bw.close();
            BufferedWriter chkWriter = new BufferedWriter(new FileWriter(outChkFile));
            chkWriter.write(firstFileName + spliter + String.valueOf(lineCount));
            chkWriter.flush();
            chkWriter.close();
        }
    }

    @Test
    public void demo4() throws Exception {
        String filePath = "demo_data";
        File chkFile = new File(filePath);
        File[] chkFileList = chkFile.listFiles((f, name) -> name.contains("CHK"));
        Set<String> phoneSet = new HashSet<>();
        for (File checkFile : chkFileList) {
            BufferedReader br = new BufferedReader(new FileReader(checkFile));
            String line;
            String spliter = new String(new byte[]{0x01});
            int index = 0;
            int lineCount = 0;
            String firstFileName = null;
            while ((line = br.readLine()) != null) {
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                String[] split = line.split(spliter, -1);
                String fileName = split[0];
                File avlFile = new File(chkFile.getAbsolutePath() + "/" + fileName);
                if (!avlFile.exists()) {
                    System.out.println("文件不存在，file：" + avlFile);
                }
                BufferedReader reader = new BufferedReader(new FileReader(avlFile));
                String dataLine;
                while ((dataLine = reader.readLine()) != null) {
                    lineCount++;
                    String[] data = dataLine.split(spliter, -1);
                    phoneSet.add(data[0]);

                }
                reader.close();
                index++;
            }
        }
        phoneSet.forEach(System.out::println);
    }

    @Test
    public void demo5() throws Exception {
        //Camel 的上下文对象
        CamelContext context = new DefaultCamelContext();
        // 添加路由
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/data/camel/source?scheduler=spring&scheduler.cron=0/10 * * * * ?")
                        .to("ftp://192.168.100.2/?username=vagrant&password=vagrant&passiveMode=true");
            }
        });

        //启动
        context.start();
        while (true) {

        }
    }

    @Test
    public void demo6() throws Exception {
        //匿名登录（无需帐号密码的FTP服务器）
        Ftp ftp = new Ftp("192.168.100.2", 21, "vagrant", "vagrant",Charset.defaultCharset(),FtpMode.Passive);
//进入远程目录
        ftp.cd("/home/vagrant/ftp");
//下载远程文件
        ftp.download("/home/vagrant/ftp", "test.tgz", FileUtil.file("d:/data/a-download.txt"));
       // ftp.getClient().rename("/home/vagrant/ftp/test.tgz","/home/vagrant/ftp/test2.tgz");
//关闭连接
        ftp.close();
    }
}
