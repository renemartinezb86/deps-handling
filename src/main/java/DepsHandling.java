import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

public class DepsHandling {
    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream in = null;
        InputStream config = ClassLoader.getSystemResourceAsStream("config.properties");
        try {
            prop.load(config);
            if (args.length > 0) {
                in = new FileInputStream(new File(args[0]));
            } else {
                in = new FileInputStream(new File(prop.getProperty("filepath")));
            }
            Object deps = new Yaml().load(in);
            ArrayList dependencies = (ArrayList) ((LinkedHashMap) deps).get("dependencies");
            System.out.println("Loaded object type:" + ReflectionToStringBuilder.toString(deps));
            PrintWriter writer = new PrintWriter(prop.getProperty("output"), "UTF-8");
            if (checkDeps(prop, dependencies)) {
                writer.println("true");
            } else {
                writer.println("false");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean checkDeps(Properties prop, ArrayList dependencies) {
        for (Object dep : dependencies) {
            String systemDep = (String) ((LinkedHashMap) dep).get("system");
            String systemVer = (String) ((LinkedHashMap) dep).get("version");

        }
        boolean result = false;
        return result;
    }
}
