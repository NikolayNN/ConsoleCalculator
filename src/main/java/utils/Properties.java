package utils;

import java.io.IOException;

public class Properties {

    private static final String FILENAME = "app.properties";

    public static String read(String propertyName) {
        java.util.Properties properties = new java.util.Properties();
        ClassLoader loader = Properties.class.getClassLoader();
        try {
            properties.load(loader.getResourceAsStream(FILENAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }

    public static int readInt(String propertyName) {
        java.util.Properties properties = new java.util.Properties();
        ClassLoader loader = Properties.class.getClassLoader();
        try {
            properties.load(loader.getResourceAsStream(FILENAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return Integer.parseInt(properties.getProperty(propertyName));
        } catch (Exception ex){
            throw new RuntimeException(String.format("Can't read property '%s' from file '%s'. %s",
                    propertyName, FILENAME, ex.getMessage()));
        }
    }
}