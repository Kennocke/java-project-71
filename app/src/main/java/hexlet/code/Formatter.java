package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> diff, String mode) {
        switch (mode) {
            case "stylish":
                return formatAsStylish(diff);
            default:
                throw new RuntimeException("Format not supported");
        }
    }

    private static String formatAsStylish(List<Map<String, Object>> diff) {
        StringBuilder builder = new StringBuilder("{\n");
        for (Map<String, Object> row : diff) {
            builder.append("    ");
            builder.append(row.get("result") != null ? row.get("result") : " ");
            builder.append(" ");
            builder.append(row.get("key"));
            builder.append(" : ");
            builder.append(row.get("value"));
            builder.append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
