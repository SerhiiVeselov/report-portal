package core;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

public class ConfigReader {

    private static LinkedHashMap<String, Object> properties;

    public ConfigReader(String filePath) {
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            properties = yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object getProperty(String environment, String property) {
        return ((LinkedHashMap) properties.get(environment)).get(property);
    }

    ConfigReader cr = new ConfigReader("src/main/resources/config.yaml");
}
