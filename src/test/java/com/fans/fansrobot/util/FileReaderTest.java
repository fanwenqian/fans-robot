package com.fans.fansrobot.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Pattern;

public class FileReaderTest {

    private static final String PATTERN = "^(?!机器人)$";

    @Test
    public void test() throws IOException {
        String str = "1机器人1";
        Pattern regexp = Pattern.compile(PATTERN);
        boolean matches = regexp.matcher(str).matches();
        System.out.println(matches);
    }
}
