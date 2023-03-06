package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    public static String genDiff(String filepath1, String filepath2) {
        try {
            String firstFileData = readFile(filepath1);
            String secondFileData = readFile(filepath2);

            Map<String, String> firstFileJSON = parse(firstFileData);
            Map<String, String> secondFileJSON = parse(secondFileData);

            StringBuilder result = new StringBuilder("{\n");
            TreeSet<String> keys = new TreeSet<>(firstFileJSON.keySet());
            keys.addAll(secondFileJSON.keySet());

            for (String key : keys) {
                String value1 = firstFileJSON.get(key);
                String value2 = secondFileJSON.get(key);

                if (firstFileJSON.containsKey(key) && secondFileJSON.containsKey(key)) {
                    if (value1.equals(value2)) {
                        formatString(result, key, value1, null);
                    } else {
                        formatString(result, key, value1, "-");
                        formatString(result, key, value2, "+");
                    }
                } else if (firstFileJSON.containsKey(key) && !secondFileJSON.containsKey(key)) {
                    formatString(result, key, value1, "-");
                } else {
                    formatString(result, key, value2, "+");
                }
            }
            result.append("}");
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static void formatString(StringBuilder builder, String key, String value, String sign) {
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
    public static String readFile(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new IOException("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }
    public static Map<String, String> parse(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }
}
