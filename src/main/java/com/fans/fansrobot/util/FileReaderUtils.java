package com.fans.fansrobot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * 文件读取工具类
 *
 * @author fans
 * @date 2021/12/31
 */
@Slf4j
public class FileReaderUtils {

    /**
     * 类路径
     */
    public static String CLASS_PATH;

    static {
        try {
            CLASS_PATH = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件的行数读取内容
     *
     * @param filePath 文件路径
     * @param lineNum  行数
     * @return 行内容
     */
    public static String getContentByLine(String filePath, long lineNum) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + filePath);
        FileReader fileReader = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(fileReader);
        long currentLine = 0;
        while (true) {
            currentLine++;
            String content = reader.readLine();
            if (currentLine == lineNum) {
                reader.close();
                fileReader.close();
                return content;
            }
        }
    }

    /**
     * 获取文件总行数
     *
     * @param filePath 文件类路径
     * @return 总行数
     * @throws IOException io
     */
    public static long getTotalLines(String filePath) throws IOException {
        File file = ResourceUtils.getFile("classpath:" + filePath);
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        reader.skip(Long.MAX_VALUE);
        return reader.getLineNumber() + 1;
    }
}
