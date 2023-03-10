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
                  - id: file
                  + id2: file2
                    menu2: test
                  - value: File
                  + value2: {menu1=papa, id3=mama}
                  + value3: [1, 2, 3]
                }""";
            String result = Differ.generate(filePath1, filePath2, "stylish");
            assertEquals(expectingData, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void genDiffYAMLTest() {
        String filePath1 = "./src/test/resources/test1.yml";
        String filePath2 = "./src/test/resources/test2.yml";
        try {
            String expectingData = """
                {
                  - id: file
                  + id2: file2
                    menu2: test
                  - value: File
                  + value2: {menu1=papa, id3=mama}
                  + value3: [1, 2, 3]
                }""";
            String result = Differ.generate(filePath1, filePath2, "stylish");
            assertEquals(expectingData, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void genDiffOutputPlainTest() {
        String filePath1 = "./src/test/resources/test1.json";
        String filePath2 = "./src/test/resources/test2.json";
        try {
            String expectingData = """
                Property 'id' was removed
                Property 'id2' was added with value: 'file2'
                Property 'value' was removed
                Property 'value2' was added with value: [complex value]
                Property 'value3' was added with value: [complex value]""";
            String result = Differ.generate(filePath1, filePath2, "plain");
            assertEquals(expectingData, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void genDiffOutputJSONTest() {
        String filePath1 = "./src/test/resources/test1.json";
        String filePath2 = "./src/test/resources/test2.json";
        try {
            String expectingData = """
                [ {
                  "oldValue" : "file",
                  "operation" : "delete",
                  "key" : "id"
                }, {
                  "newValue" : "file2",
                  "operation" : "add",
                  "key" : "id2"
                }, {
                  "oldValue" : "test",
                  "operation" : "none",
                  "key" : "menu2"
                }, {
                  "oldValue" : "File",
                  "operation" : "delete",
                  "key" : "value"
                }, {
                  "newValue" : {
                    "menu1" : "papa",
                    "id3" : "mama"
                  },
                  "operation" : "add",
                  "key" : "value2"
                }, {
                  "newValue" : [ "1", "2", "3" ],
                  "operation" : "add",
                  "key" : "value3"
                } ]""";
            String result = Differ.generate(filePath1, filePath2, "json");
            assertEquals(expectingData, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
