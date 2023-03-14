package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filepath1, String filepath2, String printFormat) throws Exception {
        Map<String, Object> firstDataObject = getDataObject(filepath1);
        Map<String, Object> secondDataObject = getDataObject(filepath2);
        List<Map<String, Object>> comparisonResult = Comparator.compare(firstDataObject, secondDataObject);
        return Formatter.format(comparisonResult, printFormat);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
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
