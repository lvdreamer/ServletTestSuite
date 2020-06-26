package com.lvdreamer.pdfparser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import technology.tabula.CommandLineApp;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 功能 PDF读写类
 *
 * @CreateTime 2011-4-14 下午02:44:11
 */
public class PDFUtil {


    /**
     * 测试pdf文件的创建
     *
     * @param args
     */
    public static void main(String[] args) throws ParseException, IOException {
        File pdfDirectory = new File("C:\\Users\\haibo\\Desktop\\222\\");
        File[] pdfs = pdfDirectory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".pdf");
            }
        });

        List<File> fileList = Arrays.asList(pdfs);
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            }
        });

        String[] comArgs = new String[]{"--pages=all"};
//        CommandLineApp.main(args);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(CommandLineApp.buildOptions(), comArgs);
        StringBuilder stringBuilder = new StringBuilder();
        File outputFile = new File("C:\\Users\\haibo\\Desktop\\222\\result.csv");
        CommandLineApp cmdApp = new CommandLineApp(stringBuilder, cmd);
        for (File pdfFile : fileList) {
            cmdApp.extractFileTables(cmd, pdfFile);
        }
        BufferedReader br = new BufferedReader(new StringReader(stringBuilder.toString()));
        String line;
        StringBuilder strbuf = new StringBuilder();
        while ((line = br.readLine()) != null) {
            if (!line.trim().equals("")) {
                if (line.contains("2020") && !line.startsWith("2020")) {
                    strbuf.append(replace(line) + "\r\n");
                }
            }
        }

        System.out.println(strbuf.toString());

    }

    private static String replace(String s) {
        String regEx = "[' ']+"; // 一个或多个空格
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regEx);
        java.util.regex.Matcher m = p.matcher(s);
        String as = m.replaceAll(",").trim();
        return as;
    }
}
