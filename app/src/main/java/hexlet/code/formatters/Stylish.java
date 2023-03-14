package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<Map<String, Object>> diffData) {
        StringBuilder result = new StringBuilder("{");
        for (Map<String, Object> row : diffData) {
            String key = row.get("key").toString();
            String operation = row.get("operation").toString();
            String formattedOldValue = stringify(row.get("oldValue"));
            String formattedNewValue = stringify(row.get("newValue"));

            result.append("\n  ");
            switch (operation) {
                case "add":
                    result.append("+ ").append(key).append(": ").append(formattedNewValue);
                    break;
                case "delete":
                    result.append("- ").append(key).append(": ").append(formattedOldValue);
                    break;
                case "update":
                    result.append("- ").append(key).append(": ").append(formattedOldValue);
                    result.append("\n  ").append("+ ").append(key).append(": ").append(formattedNewValue);
                    break;
                case "none":
                    result.append("  ").append(key).append(": ").append(formattedOldValue);
                    break;
                default:
                    throw new RuntimeException("Unknown '" + operation + "' operation");
            }
        }
        result.append("\n}");
        return result.toString();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        return value.toString();
    }
}
