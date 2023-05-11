package utc2.itk62.store.common;

import utc2.itk62.store.Main;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = Main.class.getResourceAsStream("config/config.env")) {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
