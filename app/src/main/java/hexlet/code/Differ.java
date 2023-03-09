package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static void main(String[] args) {
        try {
            getDataObject("/Users/svitkovskiy/Projects/java-project-71/app/src/test/resources/test1.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String genDiff(String filepath1, String filepath2) throws Exception {
        Map<String, String> firstDataObject = getDataObject(filepath1);
        Map<String, String> secondDataObject = getDataObject(filepath2);

        StringBuilder result = new StringBuilder("{\n");
        TreeSet<String> keys = new TreeSet<>(firstDataObject.keySet());
        keys.addAll(secondDataObject.keySet());

        for (String key : keys) {
            String value1 = firstDataObject.get(key);
            String value2 = secondDataObject.get(key);

            if (firstDataObject.containsKey(key) && secondDataObject.containsKey(key)) {
                if (value1.equals(value2)) {
                    formatString(result, key, value1, null);
                } else {
                    formatString(result, key, value1, "-");
                    formatString(result, key, value2, "+");
                }
            } else if (firstDataObject.containsKey(key) && !secondDataObject.containsKey(key)) {
                formatString(result, key, value1, "-");
            } else {
                formatString(result, key, value2, "+");
            }
        }
        result.append("}");
        return result.toString();
    }
    private static Map<String, String> getDataObject(String filepath) throws Exception {
        String fileExtension = Paths.get(filepath).getFileName().toString().split("\\.")[1].toLowerCase();
        String fileData = readFile(filepath);
        return Parser.parse(fileData, fileExtension);
    }
    private static void formatString(StringBuilder builder, String key, String value, String sign) {
        builder.append("    ");

        if (sign != null) {
            builder.append(sign);
            builder.append(" ");
        }

        builder.append(key);
        builder.append(" : ");
        builder.append(value);
        builder.append("\n");
    }
    private static String readFile(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new IOException("File '" + path + "' does not exist");
        }

        return Files.readString(path);
    }
}
