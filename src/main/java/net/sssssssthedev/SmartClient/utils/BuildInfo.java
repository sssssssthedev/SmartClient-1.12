package net.sssssssthedev.SmartClient.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BuildInfo {

    public static String getFromProperties(String file, String value) {
        InputStream inputStream = BuildInfo.class.getResourceAsStream(file);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            return properties.getProperty(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public static String getName() {
        return getFromProperties("/build.properties", "build.name");
    }

    public static String getVersion() {
        return getFromProperties("/build.properties","build.version");
    }

    public static String getCommit() {
        return getFromProperties("/git.properties", "git.commit.id.abbrev");
    }



}
