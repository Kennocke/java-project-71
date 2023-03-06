package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DifferTest {
    private static final String FILEPATH1 = "./src/test/resources/test1.json";
    private static final String FILEPATH2 = "./src/test/resources/test2.json";
    @Test()
    public void readFileTest() throws IOException {
        String expectingData = "{\"menu2\":\"test\",\"id\":\"file\",\"value\":\"File\"}";
        String data = Differ.readFile(FILEPATH1);
        assertEquals(expectingData, data);
    }
    @Test()
    public void genDiffTest() {
        String expectingData = """
                {
                    - id : file
                    + id2 : file2
                    - menu2 : test
                    + menu2 : test2
                    - value : File
                    + value2 : File2
                }""";
        String result = Differ.genDiff(FILEPATH1, FILEPATH2);
        assertEquals(expectingData, result);
    }
}
