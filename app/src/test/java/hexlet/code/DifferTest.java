package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class DifferTest {
    @Test()
    public void genDiffJSONTest() {
        try {
            String filePath1 = "./src/test/resources/test1.json";
            String filePath2 = "./src/test/resources/test2.json";
            String expectingData = """
                {
                    - id : file
                    + id2 : file2
                    - menu2 : test
                    + menu2 : test2
                    - value : File
                    + value2 : File2
                }""";
            String result = Differ.genDiff(filePath1, filePath2);
            assertEquals(expectingData, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test()
    public void genDiffYAMLTest() {
        String filePath1 = "./src/test/resources/test1.yaml";
        String filePath2 = "./src/test/resources/test2.yaml";
        try {
            String expectingData = """
                {
                    - id : file
                    + id2 : file2
                    - menu2 : test
                    + menu2 : test2
                    - value : File
                    + value2 : File2
                }""";
            String result = Differ.genDiff(filePath1, filePath2);
            assertEquals(expectingData, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
