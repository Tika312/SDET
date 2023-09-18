package com.digitalnomads.ui.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    public final static Boolean CLEAR_COOKIES_AND_STORAGE = true;

    static {
        try {
            properties = new Properties();
            String path = "C:\\Users\\tilek\\IdeaProjects\\SDET\\ui\\src\\main\\resources\\app.properties";
            FileInputStream input = new FileInputStream(path);
            properties.load(input);
            input.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getPropertyOf(String key){
        return properties.getProperty(key).trim();
    }

}
