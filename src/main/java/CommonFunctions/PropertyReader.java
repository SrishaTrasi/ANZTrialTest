package CommonFunctions;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static String getProperty(String key) throws IOException {
        FileReader reader = new FileReader("src/test/resources/config.properties");
        Properties props = new Properties();
        props.load(reader);
        return props.getProperty(key);
    }
}
