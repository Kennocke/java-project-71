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
                      menu2 : test
                    - value : File
                    + value2 : {menu1=papa, id3=mama}
                    + value3 : [1, 2, 3]
                }""";
            String result = Differ.generate(filePath1, filePath2, "stylish");
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
                      menu2 : test
                    - value : File
                    + value2 : {menu1=papa, id3=mama}
                    + value3 : [1, 2, 3]
                }""";
            String result = Differ.generate(filePath1, filePath2, "stylish");
            assertEquals(expectingData, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
