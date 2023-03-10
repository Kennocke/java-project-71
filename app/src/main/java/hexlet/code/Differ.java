package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.HashMap;

public class Differ {
    public static void main(String[] args) {
        try {
            System.out.println(generate(
                    "/Users/svitkovskiy/Projects/java-project-71/app/src/test/resources/file1.json",
                    "/Users/svitkovskiy/Projects/java-project-71/app/src/test/resources/file2.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String generate(String filepath1, String filepath2, String printFormat) throws Exception {
        Map<String, Object> firstDataObject = getDataObject(filepath1);
        Map<String, Object> secondDataObject = getDataObject(filepath2);

        List<Map<String, Object>> diffResult = new ArrayList<>();
        TreeSet<String> keys = new TreeSet<>(firstDataObject.keySet());
        keys.addAll(secondDataObject.keySet());

        for (String key : keys) {
            if (firstDataObject.containsKey(key) && secondDataObject.containsKey(key)) {
                if (firstDataObject.get(key) == null && secondDataObject.get(key) == null) {
                    diffResult.add(insertDiffRow(key, firstDataObject.get(key)));
                } else if (!(firstDataObject.get(key) == null || secondDataObject.get(key) == null)
                        && firstDataObject.get(key).equals(secondDataObject.get(key))) {
                    diffResult.add(insertDiffRow(key, firstDataObject.get(key)));
                } else {
                    diffResult.add(updateRow(key, firstDataObject.get(key), secondDataObject.get(key)));
                }
            } else if (firstDataObject.containsKey(key) && !secondDataObject.containsKey(key)) {
                diffResult.add(deleteRow(key, firstDataObject.get(key)));
            } else {
                diffResult.add(addRow(key, secondDataObject.get(key)));
            }
        }

        return Formatter.format(diffResult, printFormat);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    private static Map<String, Object> insertDiffRow(String key, Object newValue) {
        Map<String, Object> row = new HashMap<>();

        row.put("key", key);
        row.put("oldValue", newValue);
        row.put("operation", "none");
        return row;
    }

    private static Map<String, Object> addRow(String key, Object newValue) {
        Map<String, Object> row = new HashMap<>();

        row.put("key", key);
        row.put("newValue", newValue);
        row.put("operation", "add");

        return row;
    }

    private static Map<String, Object> deleteRow(String key, Object oldValue) {
        Map<String, Object> row = new HashMap<>();

        row.put("key", key);
        row.put("oldValue", oldValue);
        row.put("operation", "delete");

        return row;
    }

    private static Map<String, Object> updateRow(String key, Object oldValue, Object newValue) {
        Map<String, Object> row = new HashMap<>();

        row.put("key", key);
        row.put("oldValue", oldValue);
        row.put("newValue", newValue);
        row.put("operation", "update");

        return row;
    }

    private static Map<String, Object> getDataObject(String filepath) throws Exception {
        String fileExtension = Paths.get(filepath).getFileName().toString().split("\\.")[1].toLowerCase();
        String fileData = readFile(filepath);
        return Parser.parse(fileData, fileExtension);
    }

    private static String readFile(String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new IOException("File '" + path + "' does not exist");
        }

        return Files.readString(path);
    }
}
