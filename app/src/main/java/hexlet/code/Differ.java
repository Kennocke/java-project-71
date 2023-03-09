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
    public static String genDiff(String filepath1, String filepath2, String printFormat) throws Exception {
        Map<String, Object> firstDataObject = getDataObject(filepath1);
        Map<String, Object> secondDataObject = getDataObject(filepath2);

        List<Map<String, Object>> diffResult = new ArrayList<>();
        TreeSet<String> keys = new TreeSet<>(firstDataObject.keySet());
        keys.addAll(secondDataObject.keySet());

        for (String key : keys) {
            if (firstDataObject.containsKey(key) && secondDataObject.containsKey(key)) {
                if (firstDataObject.get(key).equals(secondDataObject.get(key))) {
                    diffResult.add(createDiffRow(key, firstDataObject.get(key), null));
                } else {
                    diffResult.add(createDiffRow(key, firstDataObject.get(key), "-"));
                    diffResult.add(createDiffRow(key, secondDataObject.get(key), "+"));
                }
            } else if (firstDataObject.containsKey(key) && !secondDataObject.containsKey(key)) {
                diffResult.add(createDiffRow(key, firstDataObject.get(key), "-"));
            } else {
                diffResult.add(createDiffRow(key, secondDataObject.get(key), "+"));
            }
        }

        return Formatter.format(diffResult, printFormat);
    }

    private static Map<String, Object> createDiffRow(String key, Object value, String result) {
        Map<String, Object> diffRow = new HashMap<>();

        diffRow.put("key", key);
        diffRow.put("value", value);
        diffRow.put("result", result);

        return diffRow;
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
