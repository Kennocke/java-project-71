package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, Object>> diffData) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> row : diffData) {
            String key = row.get("key").toString();
            String operation = row.get("operation").toString();
            String formattedOldValue = stringify(row.get("oldValue"));
            String formattedNewValue = stringify(row.get("newValue"));

            switch (operation) {
                case "add":
                    result.append("\nProperty '")
                            .append(key)
                            .append("' was added with value: ")
                            .append(formattedNewValue);
                    break;
                case "delete":
                    result.append("\nProperty '")
                            .append(key)
                            .append("' was removed");
                    break;
                case "update":
                    result.append("\nProperty '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(formattedOldValue)
                            .append(" to ")
                            .append(formattedNewValue);
                    break;
                case "none":
                    break;
                default:
                    throw new RuntimeException("Unknown '" + operation + "' operation");
            }
        }
        return result.toString().trim();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        return value.toString();
    }
}
