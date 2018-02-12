package utils;

import java.io.IOException;
import java.util.Properties;

public class Props {

    private static final String FILENAME = "app.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        ClassLoader loader = Props.class.getClassLoader();
        try {
            properties.load(loader.getResourceAsStream(FILENAME));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Can't read property file: %s. %s", FILENAME, e.getMessage()));
        }
    }

    public static String read(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public static int readInt(String propertyName) {
        try {
            return Integer.parseInt(properties.getProperty(propertyName));
        } catch (Exception ex){
            throw new RuntimeException(String.format("Can't read property '%s' from file '%s'. %s",
                    propertyName, FILENAME, ex.getMessage()));
        }
    }
}