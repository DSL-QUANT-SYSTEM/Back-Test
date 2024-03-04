package com.example.cap2024.handler.SystemConfigHandler;

import java.io.IOException;
import java.nio.file.*;

public class IniHandler {
    public String iniPath = "";

    public IniHandler() {
        this(Paths.get(System.getProperty("user.dir"), "SystemConfig.ini").toString());
    }

    public IniHandler(String path) {
        iniPath = path;
    }

    public void WriteIni(String section, String key, String value) {
        try {
            String content = String.format("%s=%s%n", key, value);
            Files.writeString(Paths.get(iniPath), content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadIni(String section, String key) {
        try {
            String content = Files.readString(Paths.get(iniPath));
            String[] lines = content.split("\n");

            for (String line : lines) {
                String trimmedLine = line.trim();
                if (trimmedLine.startsWith(section) && trimmedLine.contains(key)) {
                    String[] keyValue = trimmedLine.split("=");
                    if (keyValue.length == 2 && keyValue[0].trim().equals(key)) {
                        return keyValue[1].trim();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) {
        IniHandler iniHandler = new IniHandler();
        iniHandler.WriteIni("Section1", "Key1", "Value1");
        System.out.println(iniHandler.ReadIni("Section1", "Key1"));
    }
}
