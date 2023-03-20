package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;


public class DifferTest {
    private static Map<String, String> expectedResults;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName).toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath);
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedResults = Map.of("json", readFixture("result_json.json"),
                        "plain", readFixture("result_plain.txt"),
                        "stylish", readFixture("result_stylish.txt"),
                        "default", readFixture("result_stylish.txt"));
    }

    private static Stream<Arguments> getFileFormatAndOutputFormat() {
        return Stream.of(
                arguments("yml", "json"),
                arguments("yml", "stylish"),
                arguments("yml", "plain"),
                arguments("yml", "default"),
                arguments("yaml", "json"),
                arguments("yaml", "stylish"),
                arguments("yaml", "plain"),
                arguments("yaml", "default"),
                arguments("json", "json"),
                arguments("json", "stylish"),
                arguments("json", "plain"),
                arguments("json", "default")
        );
    }

    @ParameterizedTest
    @MethodSource("getFileFormatAndOutputFormat")
    public void generateTest(String fileFormat, String outputFormat) throws Exception {
        String filePath1 = getFixturePath("data1." + fileFormat).toString();
        String filePath2 = getFixturePath("data2." + fileFormat).toString();

        String result = outputFormat.equals("default")
                ? Differ.generate(filePath1, filePath2)
                : Differ.generate(filePath1, filePath2, outputFormat);
        assertEquals(expectedResults.get(outputFormat), result);
    }
}
