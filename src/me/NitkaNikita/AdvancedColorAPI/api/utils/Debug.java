package me.NitkaNikita.AdvancedColorAPI.api.utils;

import java.util.logging.Logger;

public class Debug {
    private static Logger logger;

    public static void setLogger(Logger logger) {
        Debug.logger = logger;
    }

    public static void Log(String str){
        logger.info(str);
    }
}
