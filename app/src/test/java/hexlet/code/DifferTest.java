package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DifferTest {
    private static String resultJSON;
    private static String resultPlain;
    private static String resultStylish;
    private static String firstFileJSONPath;
    private static String secondFileJSONPath;
    private static String firstFileYAMLPath;
    private static String secondFileYAMLPath;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath);
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        firstFileJSONPath = getFixturePath("data1.json").toString();
        secondFileJSONPath = getFixturePath("data2.json").toString();
        firstFileYAMLPath = getFixturePath("data1.yaml").toString();
        secondFileYAMLPath = getFixturePath("data2.yml").toString();

        resultJSON = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    @Test()
    public void compareJSONAsStylishTest() {
        try {
            String result = Differ.generate(firstFileJSONPath, secondFileJSONPath, "stylish");
            assertEquals(resultStylish, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void compareYAMLAsStylishTest() {
        try {
            String result = Differ.generate(firstFileYAMLPath, secondFileYAMLPath, "stylish");
            assertEquals(resultStylish, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void compareJSONAsPlainTest() {
        try {
            String result = Differ.generate(firstFileJSONPath, secondFileJSONPath, "plain");
            assertEquals(resultPlain, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void compareJSONAsJSONTest() {
        try {
            String result = Differ.generate(firstFileJSONPath, secondFileJSONPath, "json");
            assertEquals(resultJSON, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
