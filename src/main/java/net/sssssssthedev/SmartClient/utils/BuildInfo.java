package net.sssssssthedev.SmartClient.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BuildInfo {

    public static String GetFromProperties(String value) {
        InputStream inputStream = BuildInfo.class.getResourceAsStream("/build.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            return properties.getProperty(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public static String GetName() {
        return GetFromProperties("build.name");
    }

    public static String GetVersion() {
        return GetFromProperties("build.version");
    }



}
