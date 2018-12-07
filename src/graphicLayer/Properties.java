package graphicLayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Properties {
    private static Properties ourInstance = new Properties();

    public static Properties getInstance() {
        return ourInstance;
    }

    private Properties() {
    }

    public String getPropertie(String param){
        final java.util.Properties prop = new java.util.Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/Milieu.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop.getProperty(param);


        } catch (final IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
