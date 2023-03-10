package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String stringToParse, String fileFormat) throws Exception {
        return switch (fileFormat) {
            case "json" -> parseJSON(stringToParse);
            case "yml" -> parseYAML(stringToParse);
            default -> throw new RuntimeException("Unknown file format");
        };
    }

    private static Map<String, Object> parseJSON(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

    private static Map<String, Object> parseYAML(String yaml) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(yaml, Map.class);
    }
}
