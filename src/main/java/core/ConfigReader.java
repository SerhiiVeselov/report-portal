package core;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;


public class ConfigReader {

    private static final String DEFAULT_ENV = "local";
    private static final String ENV_VARIABLE_NAME = "env";
    private static Map<String, Object> environmentConfig;

    static {
        String env = System.getProperty(ENV_VARIABLE_NAME, System.getenv(ENV_VARIABLE_NAME));
        if (env == null) {
            env = DEFAULT_ENV;
        }
        Yaml yaml = new Yaml();
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.yaml");
        Map<String, Object> config = yaml.load(inputStream);

        environmentConfig = (Map<String, Object>) config.get(env);
    }

    public static String getUrl() {
        return (String) environmentConfig.get("url");
    }

    public static String getUsername() {
        return (String) environmentConfig.get("username");
    }

    public static String getPassword() {
        return (String) environmentConfig.get("password");
    }
}

