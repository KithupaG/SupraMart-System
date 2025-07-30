package lk.supramart.util;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.*;
import javax.swing.JOptionPane;

public class LoggerUtil {
    private static final String LOG_FILE_NAME = "supramart.log";
    private static final Level LOG_LEVEL = Level.ALL;
    private static final FileHandler fileHandler;
    private static final Map<Class<?>, Logger> loggerCache = new ConcurrentHashMap<>();

    static {
        FileHandler fh = null;
        try {
            fh = new FileHandler(LOG_FILE_NAME, true);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(LOG_LEVEL);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "LoggerUtil IO Exception: " + e.getMessage(),
                    "IO Exception",
                    JOptionPane.ERROR_MESSAGE);
        }
        fileHandler = fh;
    }

    public static Logger getLogger(Class<?> clazz) {
        return loggerCache.computeIfAbsent(clazz, LoggerUtil::createLogger);
    }

    private static Logger createLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.setUseParentHandlers(false);
        if (fileHandler != null) {
            logger.addHandler(fileHandler);
        }
        logger.setLevel(LOG_LEVEL);
        return logger;
    }

    private static void log(Class<?> clazz, Level level, String message) {
        Logger logger = getLogger(clazz);
        LogRecord record = new LogRecord(level, message);
        record.setLoggerName(clazz.getName());
        record.setSourceClassName(clazz.getName());
        record.setSourceMethodName("");
        logger.log(record);
    }

    public static class Log {
        public static void info(Class<?> clazz, String message) {
            log(clazz, Level.INFO, message);
        }

        public static void warning(Class<?> clazz, String message) {
            log(clazz, Level.WARNING, message);
        }

        public static void severe(Class<?> clazz, String message) {
            log(clazz, Level.SEVERE, message);
        }

        public static void fine(Class<?> clazz, String message) {
            log(clazz, Level.FINE, message);
        }

        public static void finer(Class<?> clazz, String message) {
            log(clazz, Level.FINER, message);
        }

        public static void finest(Class<?> clazz, String message) {
            log(clazz, Level.FINEST, message);
        }
    }

    public static boolean isInitialized() {
        return fileHandler != null;
    }

    public static String getLogFileName() {
        return LOG_FILE_NAME;
    }

    public static void clearCache() {
        loggerCache.clear();
    }

    public static void shutdown() {
        if (fileHandler != null) {
            fileHandler.flush();
            fileHandler.close();
        }
    }
} 
