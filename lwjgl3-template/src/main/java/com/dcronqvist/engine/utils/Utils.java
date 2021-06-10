package com.dcronqvist.engine.utils;

import java.io.FileReader;

public class Utils {
    public static String getTextFromFile(String path) throws Exception {
        try (FileReader fr = new FileReader(path)) {
            StringBuilder fileContent = new StringBuilder();
            int content;
            while ((content = fr.read()) != -1) {
                fileContent.append((char) content);
            }
            return fileContent.toString();
        } catch (Exception e) {
            throw e;
        }
    }
}
