package utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private static final String PATH = System.getProperty("user.dir")+"/TestProject-main/src/main/resources/logs/";
    private static final String FILE_NAME = "app.log";
    private static final Logger logger;

    static {
        logger = Logger.getLogger("MyLog");
        final FileHandler fh;
        try {
            final File myObj = new File(PATH + FILE_NAME);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (final IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            fh = new FileHandler(PATH + FILE_NAME, true);
            logger.addHandler(fh);
            final SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (final SecurityException | IOException e) {
            e.printStackTrace();
        }
    }


    public static Logger getLogger() {
        return logger;
    }


}
