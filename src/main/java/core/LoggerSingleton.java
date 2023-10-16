package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerSingleton {

    private static Logger log;

    private LoggerSingleton() {}

    public static Logger getLogger() {
        if (log == null) {
            log =  LoggerFactory.getLogger(LoggerSingleton.class);
        }
        return log;
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void error(String errorMessage) {
        log.info(errorMessage);
    }
}
